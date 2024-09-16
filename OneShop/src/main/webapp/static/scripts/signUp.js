document.addEventListener("DOMContentLoaded", function() {
    
    document.querySelector("input[name='username']").addEventListener("blur", function() {
        checkField("username", this.value);
    });

    document.querySelector("input[name='email']").addEventListener("blur", function() {
        checkField("email", this.value);
    });

    document.querySelector("input[name='phone']").addEventListener("blur", function() {
        checkField("phone", this.value);
    });
});

function checkField(field, value) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/OneShop/check-user", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);

            if (field === "username") {
                document.getElementById("username-error").innerText = response.isUsernameTaken ? "Tên đăng nhập đã được sử dụng." : "";
            } else if (field === "email") {
                document.getElementById("email-error").innerText = response.isEmailTaken ? "Email đã được sử dụng." : "";
            } else if (field === "phone") {
                document.getElementById("phone-error").innerText = response.isPhoneTaken ? "Số điện thoại đã được sử dụng." : "";
            }
        }
    };

    xhr.send(field + "=" + encodeURIComponent(value));
}
