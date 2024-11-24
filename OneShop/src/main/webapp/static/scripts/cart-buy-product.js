function incrementQuantity() {
            var quantityInput = document.getElementById('quantity');
            quantityInput.value = parseInt(quantityInput.value) + 1;
        }

function decrementQuantity() {
    var quantityInput = document.getElementById('quantity');
    if (parseInt(quantityInput.value) > 1) {
        quantityInput.value = parseInt(quantityInput.value) - 1;
    }
}
function addToCart(productId, quantity, userId) {
	
    const params = new URLSearchParams({
        productId: productId,
        quantity: quantity,
        userId: userId
    });

    fetch('/OneShop/user/add-to-cart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded', 
        },
        body: params.toString() 
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            const cartCount = document.getElementById('cartItemCount');
            if (cartCount) {
                cartCount.innerText = data.cartItemCount;  
            }
            alert("Thêm vào giỏ hàng thành công!");
        } else {
            alert(data.message || "Có lỗi xảy ra!");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Không thể thêm vào giỏ hàng. Vui lòng thử lại sau.");
    });
}


//--------------------------------------------------------------
function buyNow(productId, quantity, userId) {
    if (!productId || !userId) {
        alert("Vui lòng đăng nhập hoặc chọn sản phẩm hợp lệ.");
        return;
    }

    fetch('/OneShop/user/buy-now', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            productId: productId,
            quantity: quantity,
            userId: userId,
        }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // Chuyển hướng đến trang thanh toán
            //window.location.href = '/OneShop/user/checkout';
        } else {
            alert(data.message || "Có lỗi xảy ra!");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Không thể mua ngay. Vui lòng thử lại sau.");
    });
}
