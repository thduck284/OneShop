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
	const token = localStorage.getItem('customerToken');

    if (!userId) {
        alert('Bạn cần đăng nhập để thanh toán.');
        return;
    }

    if (!token) {
        alert('Token không tồn tại. Vui lòng đăng nhập lại.');
        window.location.href = '/OneShop/login';
        return;
    }
	
    fetch('/OneShop/buy-now', {
        method: 'POST',
        headers: {
        	'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Bearer ' + token
        },
        body: new URLSearchParams({
        	userId: userId,      
            productId: productId
        })
    })
    .then(response => {
        if (response.ok) {
        	window.location.href = '/OneShop/awaiting-payment';
        } else if (response.status === 401) {
            alert('Token không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại.');
            localStorage.removeItem('customerToken');
            window.location.href = '/OneShop/login';
        } else {
            console.error('Lỗi thanh toán: ' + response.status);
            alert('Có lỗi xảy ra khi thanh toán. Vui lòng thử lại sau.');
        }
    })
    .catch(error => {
        console.error('Lỗi:', error);
        alert('Có lỗi xảy ra khi kết nối với hệ thống.');
    });
}
