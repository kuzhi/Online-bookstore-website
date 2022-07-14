<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

 <c:url var="url" value="/kgbBookstore.com/kich-hoat"/>
	<div class=row>
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card shadow-2-strong" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">
							<h3 class="mb-5">Kích hoạt tài khoản</h3>
							<form action="${url}" method="post">
								<div class="form-outline mb-4">
									<input type="text" name="maKichHoat"
										class="form-control form-control-lg"
										placeholder="Nhập mã xác minh tại đây" required />
								</div>
								<c:if test="${not empty message}">
									<div class="form-group">
										<div class="alert alert-success">${message}</div>
									</div>
								</c:if>
								<c:if test="${not empty error}">
									<div class="form-group">
										<div class="alert alert-danger">${error}</div>
									</div>
								</c:if>
								<button class="form-control form-control-lg btn btn-success btn-lg btn-block mb-3">
									KÍCH HOẠT
								</button>
							</form>
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>