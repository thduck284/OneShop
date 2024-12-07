<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" style="margin: 15px 0 0 230px;">
        <h2 class="text-center mb-4" style="font-weight: bold;">Xem doanh thu</h2>
        <form action="${pageContext.request.contextPath}/admin/revenue" method="post">
            <div class="form-group mb-4">
                <label for="timePeriod" style="margin-bottom: 10px; font-size: 25px;">Chọn khoảng thời gian:</label>
                <div>
                    <input type="radio" id="day" name="timePeriod" value="day" style="font-size: 30px;" required>
                    <label for="day">Theo ngày</label>
                    <input type="radio" id="month" name="timePeriod" value="month" style="margin-left: 50px; font-size: 30px;">
                    <label for="month">Theo tháng</label>
                    <input type="radio" id="quarter" name="timePeriod" value="quarter" style="margin-left: 50px; font-size: 30px;">
                    <label for="quarter">Theo quý</label>
                    <input type="radio" id="year" name="timePeriod" value="year" style="margin-left: 50px; font-size: 30px;">
                    <label for="year">Theo năm</label>
                </div>
                <div id="timeInput" style="margin-top: 20px;">
                    <div id="dayInput" style="display:none;">
                        <label for="dayDate" style="font-size: 25px;">Chọn ngày:</label>
                        <input type="date" id="dayDate" name="dayDate" class="form-control" style="font-size: 16px;">
                    </div>
                    <div id="monthInput" style="display:none;">
                        <label for="monthSelect" style="font-size: 25px;">Chọn tháng:</label>
                        <select id="monthSelect" name="monthSelect" class="form-control" style="font-size: 16px;">
                            <option value="1">Tháng 1</option>
                            <option value="2">Tháng 2</option>
                            <option value="3">Tháng 3</option>
                            <option value="4">Tháng 4</option>
                            <option value="5">Tháng 5</option>
                            <option value="6">Tháng 6</option>
                            <option value="7">Tháng 7</option>
                            <option value="8">Tháng 8</option>
                            <option value="9">Tháng 9</option>
                            <option value="10">Tháng 10</option>
                            <option value="11">Tháng 11</option>
                            <option value="12">Tháng 12</option>
                        </select>
                    </div>
                    <div id="quarterInput" style="display:none;">
                        <label for="quarterSelect" style="font-size: 25px;">Chọn quý:</label>
                        <select id="quarterSelect" name="quarterSelect" class="form-control" style="font-size: 16px;">
                            <option value="1">Quý 1</option>
                            <option value="2">Quý 2</option>
                            <option value="3">Quý 3</option>
                            <option value="4">Quý 4</option>
                        </select>
                    </div>
                    <div id="yearInput" style="display:none;">
                        <label for="yearInput" style="font-size: 25px;">Nhập năm:</label>
                        <input type="number" id="yearInput" name="yearInput" class="form-control" style="font-size: 16px;">
                    </div>
                </div>
            </div>
            <div class="form-group mb-4">
                <label for="vendorIdSelected" style="font-size: 25px;">Chọn mã người bán:</label>
                <select id="vendorIdSelected" name="vendorIdSelected" class="form-control" style="font-size: 16px;">
			        <c:forEach var="vendor" items="${listVendor}">
			            <option value="${vendor.userId}">${vendor.userId}</option>
			        </c:forEach>
			    </select>            
			</div>
            <div class="form-group mb-4">
                <label for="profitPercentage" style="margin: 5px 0 5px 0; font-size: 25px;">Lợi nhuận (%):</label>
                <input type="number" id="profitPercentage" name="profitPercentage" class="form-control" style="margin-top: 5px;" placeholder="Nhập % lợi nhuận" required>
            </div>
            <div class="form-group mb-4">
                <button type="submit" class="btn btn-success" style="margin: 20px 0 5px 0; font-size: 18px; background-color: #28a745; color: black;">Tính Doanh Thu</button>
            </div>
        </form>
        <div class="form-group" style="margin-bottom: 50px;">
            <label for="calculatedRevenue" style="margin: 5px 0 5px 0; font-size: 25px;">Doanh thu tính toán:</label> <input type="text" id="calculatedRevenue"
				class="form-control"
				value="${not empty calculatedRevenue ? calculatedRevenue : ''}"
				style="margin-top: 5px;" disabled>
		</div>      
    </div>
    <script>
        document.querySelectorAll('input[name="timePeriod"]').forEach(radio => {
            radio.addEventListener('change', function() {
                document.getElementById('dayInput').style.display = 'none';
                document.getElementById('monthInput').style.display = 'none';
                document.getElementById('quarterInput').style.display = 'none';
                document.getElementById('yearInput').style.display = 'none';

                if (this.value === 'day') {
                    document.getElementById('dayInput').style.display = 'block';
                } else if (this.value === 'month') {
                    document.getElementById('monthInput').style.display = 'block';
                } else if (this.value === 'quarter') {
                    document.getElementById('quarterInput').style.display = 'block';
                } else if (this.value === 'year') {
                    document.getElementById('yearInput').style.display = 'block';
                }
            });
        });
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script>
</body>
</html>