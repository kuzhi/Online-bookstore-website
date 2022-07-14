package pc02674.asmhoanchinh.services;


import pc02674.asmhoanchinh.entity.GioHang;



public interface ShoppingCartService {
	
	void add(String id,GioHang giohang);
	
	void add(String id,GioHang giohang,int soLuong);
	
	void remove(Long id);

	
	void update(Long id, int qty);

	void clear(int id, String users);

	

	
	int getCount(int id);

	double getAmount(int id);
}
