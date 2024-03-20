//cái này là có top
let html = `
    <div class = "book-color-top">
        
    </div>
`;

//cái này dùng khi không top
let html2 = `
    <div class = "book-color-left2">
        
    </div>
`;
let html3 = `
    <div class = "book-color-left3">
        
    </div>
`;

window.onload = function () {
  // truy cập đến phần tử con cả nó
  const warapperObj1 = document.querySelector(
    "body > #wrap > #maincontent > #theatre-ia-wrap > #theatre-ia > .row > .xs-col-12 > .focus-on-child-only > #IABookReaderMessageWrapper"
  );
  if (warapperObj1 !== null) {
    warapperObj1.innerHTML += html;
    const warapperObj2 = document.querySelector(
      "body > #wrap > #maincontent > #theatre-ia-wrap > #theatre-ia > .row > .xs-col-12"
    );
    warapperObj2.innerHTML += html2;
    warapperObj2.innerHTML += html3;

    setInterval(() => {
      // Lấy đối tượng URL hiện tại
      var currentURL = new URL(window.location.href);
      // Thay đổi phần path của URL
      currentURL.pathname = "/";
      // Cập nhật địa chỉ URL mà không làm tải lại trang
      window.history.replaceState({}, "", currentURL.href);
    }, 550);
  }
};
