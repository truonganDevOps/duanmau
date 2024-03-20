/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class URL_Dealer {

    // open link in default web browser
    public static void openURL(String link) {
        try {
            // Check if the Desktop API is supported on the current platform
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                // Create a URI object from the URL
                URI uri = new URI(link);

                // Open the default web browser to the specified URL
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Desktop API is not supported on this platform.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
//            System.out.println(e.toString());
            DialogHelper.alert(null, e.toString()
            );

        }
    }

    public static void downloadImage(String imageUrl, boolean overwrite) {
        String destinationDirectory = "images/";
        try {
            URL url = new URL(imageUrl);

            // Open a stream to read data from the URL
            try (InputStream in = url.openStream()) {
                // Create the destination directory if it doesn't exist
                Path destinationPath = Path.of(destinationDirectory);
                Files.createDirectories(destinationPath);

                // Extract the file name from the URL
                String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);

                // Save the image to the destination directory
                Path destinationFile = destinationPath.resolve(fileName);

                if (Files.exists(destinationFile) && !overwrite) {
                    System.out.println("File already exists: " + destinationFile);
                } else {
                    Files.copy(in, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Image downloaded successfully to: " + destinationFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error downloading image: " + e.getMessage());
        }
    }
}
