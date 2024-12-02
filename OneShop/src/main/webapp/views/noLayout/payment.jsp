<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Product" %>
<%@ page import="models.CartDetail" %>
<%@ page import="models.Promotion" %>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thanh toán</title>
<style>
	body {
	    font-family: Arial, sans-serif;
	    margin: 0;
	    padding: 0;
	    background-color: #f8f9fa;
	}	
	.payment-container {
	    max-width: 800px;
	    margin: 50px auto;
	    padding: 20px;
	    background-color: #fff;
	    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	    border-radius: 10px;
	}	
	h1, h2, h3 {
	    color: #333;
	}	
	table {
	    width: 100%;
	    border-collapse: collapse;
	    margin: 20px 0;
	}	
	table th, table td {
	    border: 1px solid #ddd;
	    padding: 10px;
	    text-align: left;
	}	
	table th {
	    background-color: #f4f4f4;
	}	
	input, textarea {
	    width: 100%;
	    padding: 10px;
	    margin: 10px 0;
	    border: 1px solid #ddd;
	    border-radius: 5px;
	}	
	button.pay-button {
	    width: 100%;
	    padding: 15px;
	    background-color: #28a745;
	    color: #fff;
	    font-size: 16px;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	    transition: background-color 0.3s ease;
	}	
	button.pay-button:hover {
	    background-color: #218838;
	}	
	.payment-methods {
        margin-top: 20px;
    }
    .payment-methods input {
        margin-right: 10px;
    } 
</style>
</head>
<body>
	<div class="payment-container">
        <h1 style="display: grid; justify-content: center; margin-bottom: 50px;">Thanh Toán</h1>
        <div class="product-info">
            <h2>Thông tin sản phẩm</h2>
          	<%
			    List<String> idList = (List<String>) session.getAttribute("idList");
			    Product productOrder = (Product) session.getAttribute("productOrder");
			%>
            <table>
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Giá</th>
                        <th>Tổng</th>
                    </tr>
                </thead>
                <tbody>
				    <c:if test="${not empty productOrder}">
				        <tr>
				            <td>${productOrder.productName}</td>
				            <td>${productOrder.quantity}</td>
				            <td>${productOrder.price} VND</td>
				            <td>${productOrder.price * productOrder.quantity} VND</td>
				        </tr>
				    </c:if>
				    <c:if test="${not empty sessionScope.lstCartDetails}">
					    <c:forEach var="cartDetail" items="${sessionScope.lstCartDetails}">
					        <tr>
					            <td>${cartDetail.productName}</td>
					            <td>${cartDetail.quantity}</td>
					            <td>${cartDetail.price} VND</td>
					            <td>${cartDetail.price * cartDetail.quantity} VND</td>
					        </tr>
					    </c:forEach>
					</c:if>
				</tbody>
				<tfoot>
			        <tr>
			            <td colspan="3" style="text-align: right;">Tổng tiền:</td>
			            <td id="totalAmountDisplay">
				            <c:set var="totalAmount" value="0" />
				            <c:if test="${not empty productOrder}">
				                <c:set var="totalAmount" value="${totalAmount + (productOrder.price * productOrder.quantity)}" />
				            </c:if>
				            <c:if test="${not empty sessionScope.lstCartDetails}">
				                <c:forEach var="cartDetail" items="${sessionScope.lstCartDetails}">
				                    <c:set var="totalAmount" value="${totalAmount + (cartDetail.price * cartDetail.quantity)}" />
				                </c:forEach>
				            </c:if>
				            ${totalAmount} VND
				        </td>
			        </tr>
			    </tfoot>
            </table>
        </div>
        <div class="promotion-section" style="margin-top: 20px;">
		    <h3>Chọn khuyến mãi áp dụng</h3>
		    <label for="promotionSelect">Khuyến mãi:</label>
		    <select id="promotionSelect" name="promotionId" style="width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ddd; border-radius: 5px;">
		        <option value="">-- Chọn khuyến mãi --</option>
		        <c:forEach var="promo" items="${sessionScope.promotion}">
		            <option value="${promo.promotionId}">${promo.promotionId} - Giảm ${promo.price}%</option>
		        </c:forEach>
		    </select>
		</div>
        <div class="customer-info">
            <h2 style="display: grid; margin-top: 40px;">Thông tin khách hàng</h2>
            <p><strong>Họ và Tên:</strong> ${userInfor.fullName}</p>
    		<p><strong>Số điện thoại:</strong> ${userInfor.phoneNumber}</p>
    		<p><strong>Địa chỉ giao hàng:</strong> ${userInfor.address}</p>
        </div>
        <div class="payment-methods">
		    <h3>Chọn phương thức thanh toán</h3>
		    <label style="display: grid;">
		        <input type="radio" name="paymentMethod" style="margin: 0 0 -20px 0;" value="creditCard" onclick="showPaymentOptions()"> Thẻ tín dụng
		    </label><br>
		    <label style="display: grid;">
		        <input type="radio" name="paymentMethod" style="margin: 0 0 -20px 0;" value="eWallet" onclick="showPaymentOptions()"> Ví điện tử
		    </label><br>
		    <label style="display: grid;">
		        <input type="radio" name="paymentMethod" style="margin: 0 0 -20px 0;" value="cod" onclick="showPaymentOptions()"> Thanh toán khi nhận hàng
		    </label>
		</div>
		<div class="payment-options">
		    <div id="creditCardFields" class="credit-card-fields" style="display:none;">
		        <h4>Thông tin thẻ tín dụng</h4>
		        <label for="cardNumber">Số thẻ:</label>
		        <input type="text" id="cardNumber" name="cardNumber" placeholder="Nhập số thẻ tín dụng" required>
		        <label for="expiryDate">Ngày hết hạn:</label>
		        <input type="date" id="expiryDate" name="expiryDate" placeholder="MM/YY" required>
		        <label for="cvv">CVV:</label>
		        <input type="text" id="cvv" name="cvv" placeholder="CVV" required>
		        <label for="bank">Chọn ngân hàng:</label>
		        <select id="bank" name="bank">
		            <option value="Thẻ tín dụng (Vietcombank)">Vietcombank</option>
		            <option value="Thẻ tín dụng (Techcombank)">Techcombank</option>
		            <option value="Thẻ tín dụng (BIDV)">BIDV</option>
		            <option value="Thẻ tín dụng (Argibank)">Agribank</option>
		        </select>
		    </div>		
		    <div id="eWalletFields" class="e-wallet-fields" style="display:none;">
		        <h4>Thông tin Ví điện tử</h4>
		        <label for="walletName">Tên ví điện tử:</label>
		        <input type="text" id="walletName" name="walletName" placeholder="Nhập tên ví điện tử" required>
		        <label for="walletId">ID Ví:</label>
		        <input type="text" id="walletId" name="walletId" placeholder="Nhập ID ví" required>
		        <label for="eWalletProvider">Chọn ví điện tử:</label>
		        <select id="eWalletProvider" name="eWalletProvider">
		            <option value="Ví điện tử (Momo)">Momo</option>
		            <option value="Ví điện tử (ZaloPay)">ZaloPay</option>
		            <option value="Ví điện tử (VNPAY)">VNPAY</option>
		            <option value="Ví điện tử (AirPay)">AirPay</option>
		        </select>
		    </div>
		</div>
        <button onclick="processFinalPayment()" class="pay-button" style="margin-top: 50px;">Thanh toán</button>
    </div>
    <script>
	    function showPaymentOptions() {
	        var creditCardFields = document.getElementById("creditCardFields");
	        var eWalletFields = document.getElementById("eWalletFields");

	        creditCardFields.style.display = "none";
	        eWalletFields.style.display = "none";

	        var paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
	
	        if (paymentMethod === "creditCard") {
	            creditCardFields.style.display = "block";
	        } else if (paymentMethod === "eWallet") {
	            eWalletFields.style.display = "block";
	        }
	    }
	    
	    function processFinalPayment() {
	        const token = localStorage.getItem('customerToken');
	        const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked');
	        const totalCost = document.getElementById('totalAmountDisplay').innerText.replace(' VND', '').replace(',', '').trim();
	        const promotionSelect = document.getElementById('promotionSelect'); 
	        const promotionId = promotionSelect.value;

	        if (!paymentMethod) {
	            alert('Vui lòng chọn phương thức thanh toán.');
	            return;
	        }

	        const paymentValue = paymentMethod.value;

	        let selectedPaymentValue = '';
	        if (paymentValue === 'creditCard') { 
	            const bankSelect = document.getElementById('bank');
	            selectedPaymentValue = bankSelect.value;
	            if (!selectedPaymentValue) {
	                alert('Vui lòng chọn ngân hàng.');
	                return;
	            }
	        } else if (paymentValue === 'eWallet') {
	            const eWalletSelect = document.getElementById('eWalletProvider');
	            selectedPaymentValue = eWalletSelect.value;
	            if (!selectedPaymentValue) {
	                alert('Vui lòng chọn ví điện tử.');
	                return;
	            }
	        }

	        if (!token) {
	            alert('Token không tồn tại. Vui lòng đăng nhập lại.');
	            window.location.href = '/OneShop/login';
	            return;
	        }

	        fetch('/OneShop/payment-success', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded',
	                'Authorization': 'Bearer ' + token
	            },
	            body: new URLSearchParams({
	                paymentMethod: selectedPaymentValue,
	                totalCost: totalCost,
	                promotionId: promotionId
	            })
	        })
	        .then(response => {
	            if (response.ok) {
	                window.location.href = '/OneShop/payment-success';
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
	</script>
</body>
</html>