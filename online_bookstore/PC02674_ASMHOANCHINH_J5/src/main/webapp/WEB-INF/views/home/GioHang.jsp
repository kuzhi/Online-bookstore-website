				<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>    
	  
<c:url var = "url" value ="/kgbBookstore.com/customer/cart/update"/>
				
				
				<div class="cart-ui-content row ">
                    <div class="col-sm-8 col-xs-12">
                       <div>
                                
                         <div class="product-cart-left">
                                   
                             <div class="table-responsive">
                             <table class="table">
                            	
                               <c:forEach var="cart" items="${carts}">
										<form action="${url}/${cart.id}" method="post">
										<input type="hidden" name="id" value="${cart.id}">
										<tr>
											<td><img  src="/img/${cart.sach.images}" style="width: 200px; height:200px"></td>
											<td>${cart.sach.tenSach}</td>
											<td><input name="soLuong" value="${cart.soLuong}" type="number"
												onblur="this.form.submit()" style="width: 80px;" min=0  max="${cart.sach.soLuong}"></td>
											<td>
											
											<c:if test="${cart.sach.discount ==0}">
												<fmt:setLocale value="vi_VN"/>
					      					<fmt:formatNumber type="currency" value="${cart.sach.gia * cart.soLuong}"/>
													
											</c:if>
														
											<c:if test="${cart.sach.discount >0}">
												<fmt:setLocale value="vi_VN"/>
					      						<fmt:formatNumber type="currency" value="${cart.sach.gia * cart.soLuong * cart.sach.discount}"/>
													
											</c:if>
														
											</td>
											<td>
												<a href="/kgbBookstore.com/customer/cart/remove/${cart.id}" 
														class="text-decoration-none">
													Xóa						
												</a>
											</td>
										</tr>
									 </form>
										 
										  </c:forEach>
										 
										</table>
                                   	</div>
	                                   	
	                                   	<div class="border-product">
	                                   	</div>
	                                   	</div>
                                </div>
                            </div>
                           
                            <div class="col-sm-4 hidden-max-width-992">
                                <div class="total-cart-right">
                                    <div class="effect-scroll-cart-right">
					
                                 <div class="cart-event-promo-outer">
                                       
                                            <div class="block-totals-cart-page">
	                                            <div class="total-cart-page "><div class="title-cart-page-left">Thành tiền</div>
		                                            <div class="number-cart-page-right">
		                                            <span class="price"><fmt:setLocale value="vi_VN"/>
					      							<fmt:formatNumber type="currency" value="${totalPrice}"/>
		                                            </span>
	                                            </div>
                                            </div>
	                                            <div class="border-product">
	                                            </div>
	                                            <div class="total-cart-page title-final-total">
		                                            <div class="title-cart-page-left">
		                                            Tổng Số Tiền (gồm VAT)
		                                            </div>
		                                            <div class="number-cart-page-right">
		                                            <span class="price">
		                                            <fmt:setLocale value="vi_VN"/>
					      							<fmt:formatNumber type="currency" value="${totalPrice}"/>
												
		                                            
		                                            </span>
		                                            </div>
	                                            </div>
                                            </div>                                            <div class="checkout-type-button-cart" style="text-align: center;">
                                                <div class="method-button-cart">
                                                    <a  title="Thanh toán" class="btn btn-outline-danger " href="/kgbBookstore.com/customer/checkout" style="width: 300px; height: 50px;">Thanh toán
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="fhs_error_message_cart" style="margin-top:10px;background: white;padding:10px;display:none;"></div>
                                    </div>
                                </div>
                                </div>
                       </div>
                       
                       