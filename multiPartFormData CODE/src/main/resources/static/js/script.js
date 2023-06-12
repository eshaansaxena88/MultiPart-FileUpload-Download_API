function uploadFile() {
    var formData = new FormData();
    var fileInput = document.getElementById("fileInput");
    formData.append("file", fileInput.files[0]);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/upload");
    xhr.onload = function() {
        if (xhr.status === 200) {
            document.getElementById("responseMessage").innerText = "File uploaded successfully";
        } else {
            document.getElementById("responseMessage").innerText = "Failed to upload file";
        }
    };
    xhr.send(formData);
}