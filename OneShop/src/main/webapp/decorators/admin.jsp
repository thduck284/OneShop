<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
<title>Trang chủ</title>
</head>
<body>
	<%@ include file = "/views/admin/header.jsp" %>
	<decorator:body>
		<div class="container-fluid" style="display:grid;">
			<div class="row">
				<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 py-4">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Overview</li>
						</ol>
					</nav>
					<h1 class="h2">Dashboard</h1>
					<p>This is the homepage of a simple admin interface which is
						part of a tutorial written on Themesberg</p>
					<div class="row my-4">
						<div class="col-12 col-md-6 col-lg-3 mb-4 mb-lg-0">
							<div class="card">
								<h5 class="card-header">Customers</h5>
								<div class="card-body">
									<h5 class="card-title">345k</h5>
									<p class="card-text">Feb 1 - Apr 1, United States</p>
									<p class="card-text text-success">18.2% increase since last
										month</p>
								</div>
							</div>
						</div>
						<div class="col-12 col-md-6 mb-4 mb-lg-0 col-lg-3">
							<div class="card">
								<h5 class="card-header">Revenue</h5>
								<div class="card-body">
									<h5 class="card-title">$2.4k</h5>
									<p class="card-text">Feb 1 - Apr 1, United States</p>
									<p class="card-text text-success">4.6% increase since last
										month</p>
								</div>
							</div>
						</div>
						<div class="col-12 col-md-6 mb-4 mb-lg-0 col-lg-3">
							<div class="card">
								<h5 class="card-header">Purchases</h5>
								<div class="card-body">
									<h5 class="card-title">43</h5>
									<p class="card-text">Feb 1 - Apr 1, United States</p>
									<p class="card-text text-danger">2.6% decrease since last
										month</p>
								</div>
							</div>
						</div>
						<div class="col-12 col-md-6 mb-4 mb-lg-0 col-lg-3">
							<div class="card">
								<h5 class="card-header">Traffic</h5>
								<div class="card-body">
									<h5 class="card-title">64k</h5>
									<p class="card-text">Feb 1 - Apr 1, United States</p>
									<p class="card-text text-success">2.5% increase since last
										month</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-xl-8 mb-4 mb-lg-0">
							<div class="card">
								<h5 class="card-header">Latest transactions</h5>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th scope="col">Order</th>
													<th scope="col">Product</th>
													<th scope="col">Customer</th>
													<th scope="col">Total</th>
													<th scope="col">Date</th>
													<th scope="col"></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<th scope="row">17371705</th>
													<td>Volt Premium Bootstrap 5 Dashboard</td>
													<td>johndoe@gmail.com</td>
													<td>€61.11</td>
													<td>Aug 31 2020</td>
													<td><a href="#" class="btn btn-sm btn-primary">View</a></td>
												</tr>
												<tr>
													<th scope="row">17370540</th>
													<td>Pixel Pro Premium Bootstrap UI Kit</td>
													<td>jacob.monroe@company.com</td>
													<td>$153.11</td>
													<td>Aug 28 2020</td>
													<td><a href="#" class="btn btn-sm btn-primary">View</a></td>
												</tr>
												<tr>
													<th scope="row">17371705</th>
													<td>Volt Premium Bootstrap 5 Dashboard</td>
													<td>johndoe@gmail.com</td>
													<td>€61.11</td>
													<td>Aug 31 2020</td>
													<td><a href="#" class="btn btn-sm btn-primary">View</a></td>
												</tr>
												<tr>
													<th scope="row">17370540</th>
													<td>Pixel Pro Premium Bootstrap UI Kit</td>
													<td>jacob.monroe@company.com</td>
													<td>$153.11</td>
													<td>Aug 28 2020</td>
													<td><a href="#" class="btn btn-sm btn-primary">View</a></td>
												</tr>
												<tr>
													<th scope="row">17371705</th>
													<td>Volt Premium Bootstrap 5 Dashboard</td>
													<td>johndoe@gmail.com</td>
													<td>€61.11</td>
													<td>Aug 31 2020</td>
													<td><a href="#" class="btn btn-sm btn-primary">View</a></td>
												</tr>
												<tr>
													<th scope="row">17370540</th>
													<td>Pixel Pro Premium Bootstrap UI Kit</td>
													<td>jacob.monroe@company.com</td>
													<td>$153.11</td>
													<td>Aug 28 2020</td>
													<td><a href="#" class="btn btn-sm btn-primary">View</a></td>
												</tr>
											</tbody>
										</table>
									</div>
									<a href="#" class="btn btn-block btn-light">View all</a>
								</div>
							</div>
						</div>
						<div class="col-12 col-xl-4">
							<div class="card">
								<h5 class="card-header">Traffic last 6 months</h5>
								<div class="card-body">
									<div id="traffic-chart"></div>
								</div>
							</div>
						</div>
					</div>
				</main>
			</div>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
			integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
			integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
		<script async defer src="https://buttons.github.io/buttons.js"></script>
		<script>
			new Chartist.Line('#traffic-chart', {
				labels : [ 'January', 'Februrary', 'March', 'April', 'May',
						'June' ],
				series : [ [ 23000, 25000, 19000, 34000, 56000, 64000 ] ]
			}, {
				low : 0,
				showArea : true
			});
		</script>
	</decorator:body>
	<%@ include file = "/views/admin/left.jsp" %>
	<%@ include file = "/views/admin/footer.jsp" %>
</body>
</html>