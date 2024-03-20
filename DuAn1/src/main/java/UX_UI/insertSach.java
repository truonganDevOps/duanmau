
package UX_UI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dao.SachDAO;
import dao.TacGiaDAO;
import dao.TheLoaiDAO;
import java.io.IOException;
import library.URL_Dealer;
import model.Sach;
import model.TacGia;
import model.TheLoai;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * @author Vu Anh Khoa <vakhoa4875@gmail.com>
 */
public class insertSach {
    
    public static void main(String[] args) {
        SachDAO sachDao = new SachDAO();
        TacGiaDAO tacGiaDao = new TacGiaDAO();
        TheLoaiDAO theLoaiDao = new TheLoaiDAO();
        try {
            OkHttpClient client = new OkHttpClient();

            Gson gson = new Gson();

            // Specify the search query to get readable books
            String query = "has_fulltext:true";

            // Build the URL for the OpenLibrary Search API
            String url = "http://openlibrary.org/search.json?q=" + query;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            // Perform the HTTP GET request
            Response response = client.newCall(request).execute();

            // Check if the request was successful (HTTP status code 200)
            if (response.isSuccessful()) {
                // Get the JSON data as a string
                String jsonData = response.body().string();
//                System.out.println("JSON Response:");
//                System.out.println(jsonData);                

                JsonObject jsonResponse = gson.fromJson(jsonData, JsonObject.class);

                JsonArray docs = jsonResponse.getAsJsonArray("docs");

                if (!docs.isEmpty()) {
                    System.out.println("a");

                    // lặp qua jsonArray với mỗi phần từ trong array là jsonElement
                    // JsonElement không thể auto parse quan JsonObject
                    for (JsonElement book : docs) {
                        System.out.println("b");
                        JsonObject bookObj = book.getAsJsonObject();
                        System.out.println("c");

                        String idSach = bookObj.get("key").getAsString();
                        System.out.println(idSach);
                        if (sachDao.selectByID(idSach) != null) {
                            continue;
                        }
                        String tenSach = bookObj.get("title").getAsString();
                        int namXB = bookObj.getAsJsonArray("publish_year").get(0).getAsInt();
                        int namSangTac = bookObj.get("first_publish_year") == null ? 0 : bookObj.get("first_publish_year").getAsInt();
                        int soTrang = bookObj.get("number_of_pages_median") == null ? 0 : bookObj.get("number_of_pages_median").getAsInt();
                        String ebookAccess = bookObj.get("ebook_access").getAsString();
                        boolean hasFulltext = bookObj.get("has_fulltext").getAsBoolean();
                        boolean publicScanB = bookObj.get("public_scan_b").getAsBoolean();

                        //urlLink chỉ để địa chỉ đọc eBook
                        String urlLink = "https://archive.org/details/" + bookObj.getAsJsonArray("ia").get(0).getAsString() + "/mode/2up?ref=ol&view=theater";
                        // coverI là ảnh bìa quyển sách
                        String coverI = "http://covers.openlibrary.org/b/id/" + bookObj.get("cover_i").getAsString() + "-L.jpg";
                        
                        // nếu muốn tải ảnh khi pull data từ api
//                        URL_Dealer.downloadImage(coverI, false);


                        // tìm trên json méo thấy mô tả nên lấy câu đầu tiên thay thế :>>
                        JsonArray firstSentence = bookObj.get("first_sentence") == null ? null : bookObj.get("first_sentence").getAsJsonArray();
                        String moTa = "", cauDau[];
                        if (firstSentence != null) {
                            cauDau = new String[firstSentence.size()];
                            for (int i = 0; i < firstSentence.size(); i++) {
                                cauDau[i] = firstSentence.get(i).getAsString();
                            }
                            moTa = String.join(".", cauDau);
                        }

                        // do một quyển sách có thể trình bày bằng nhiều ngôn ngữ
                        JsonArray languages = bookObj.getAsJsonArray("language");
                        String[] ngonNgu = new String[languages.size()];
                        for (int i = 0; i < languages.size(); i++) {
                            ngonNgu[i] = languages.get(i).getAsString();
                        }

                        // array tác giả
                        JsonArray author_key = bookObj.getAsJsonArray("author_key");
                        JsonArray author_name = bookObj.getAsJsonArray("author_name");

                        // array thể loại
                        JsonArray subject_key = bookObj.getAsJsonArray("subject_key");
                        JsonArray subject = bookObj.getAsJsonArray("subject");

                        String phienBan = bookObj.get("_version_").getAsString();

                        Sach sach = new Sach(idSach, tenSach, namXB, namSangTac, soTrang, ebookAccess, hasFulltext, publicScanB, urlLink, coverI, moTa, ngonNgu, phienBan, 0, 0, null);
                        System.out.println(sach.toString());
                        sachDao.insert(sach);

                        if (author_key != null) {
                            for (int i = 0; i < author_key.size(); i++) {
                                String idTacGia = author_key.get(i).getAsString();
                                String tenTacGia = author_name.get(i).getAsString();
                                TacGia tacGia;

                                if (tacGiaDao.selectByID(idTacGia) == null) {
                                    tacGia = new TacGia(idTacGia, tenTacGia);
                                    tacGiaDao.insert(tacGia);
                                } else {
                                    tacGia = tacGiaDao.selectByID(idTacGia);
                                }

                                System.out.println(tacGia.toString());
                                sachDao.insertSvTG(sach, tacGia);
                            }
                        }

                        if (subject_key != null) {
                            for (int i = 0; i < subject_key.size(); i++) {
                                String idTheLoai = subject_key.get(i).getAsString();
                                String tenTheLoai = subject.get(i).getAsString();

                                TheLoai theLoai;

                                if (theLoaiDao.selectByID(idTheLoai) == null) {
                                    theLoai = new TheLoai(idTheLoai, tenTheLoai);
                                    theLoaiDao.insert(theLoai);
                                } else {
                                    theLoai = theLoaiDao.selectByID(idTheLoai);
                                }

                                System.out.println(theLoai.toString());
                                sachDao.insertSvTL(sach, theLoai);
                            }

                        }
                    }
                }

            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}

