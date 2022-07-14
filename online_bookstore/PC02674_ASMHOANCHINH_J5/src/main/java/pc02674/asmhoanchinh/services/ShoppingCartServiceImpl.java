package pc02674.asmhoanchinh.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import pc02674.asmhoanchinh.DAO.GioHangCTDAO;
import pc02674.asmhoanchinh.DAO.GioHangDAO;
import pc02674.asmhoanchinh.DAO.SachDAO;
import pc02674.asmhoanchinh.entity.DonHang;
import pc02674.asmhoanchinh.entity.DonHangCT;
import pc02674.asmhoanchinh.entity.GioHang;
import pc02674.asmhoanchinh.entity.GioHangCT;
import pc02674.asmhoanchinh.entity.Sach;


@SessionScope
@Service
public class ShoppingCartServiceImpl 
	implements ShoppingCartService{
	
	@Autowired
	GioHangCTDAO giohangCTDAO ;
	
	@Autowired
	GioHangDAO giohangDAO ;
	
	@Autowired
	SachDAO sachDao;
	
	@Override
	public void add(String id, GioHang giohang) {
		// TODO Auto-generated method stub
		Sach sach = sachDao.findById(id).get();
		GioHangCT item = giohangCTDAO.findAllByIDGioHangAndSach(giohang.getId(), "%"+id+"%");
		if(item == null) {
			 item = new GioHangCT();
				item.setSach(sach);
				item.setSoLuong(1);
				item.setGioHang(giohang);
				
		}
		else {
			item.setSoLuong(item.getSoLuong()+1);
		}
		giohangCTDAO.saveAndFlush(item);

		
	}
	
	@Override
	public void add(String id, GioHang giohang, int soLuong) {
		Sach sach = sachDao.findById(id).get();
		GioHangCT item = giohangCTDAO.findAllByIDGioHangAndSach(giohang.getId(), "%"+id+"%");
		if(item == null) {
			 item = new GioHangCT();
				item.setSach(sach);
				item.setSoLuong(soLuong);
				item.setGioHang(giohang);
				
		}
		else {
			item.setSoLuong(item.getSoLuong()+1);
		}
		giohangCTDAO.saveAndFlush(item);

		
	}
	
	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		if (giohangCTDAO.existsById(id)) {
			GioHangCT gio = giohangCTDAO.findById(id).get();
			
			giohangCTDAO.delete(gio);
		}
	}

	@Override
	public void clear(int id, String users) {
		// TODO Auto-generated method stub
		List<GioHangCT> list = giohangCTDAO.findAllByIDGioHang(id);
		
		
		for(GioHangCT item: list) {
			giohangCTDAO.delete(item);
		}
		GioHang list2 = giohangDAO.findByUser("%" + users + "%");
		giohangDAO.delete(list2);
		
		
		
	}

	
	@Override
	public int getCount(int id) {
		// TODO Auto-generated method stub
		int sl=0;
		List<GioHangCT> list = giohangCTDAO.findAllByIDGioHang(id);

		for(GioHangCT item : list) {
			sl ++;
		}
		
		return sl;
	}

	@Override
	public double getAmount(int id) {
		// TODO Auto-generated method stub
		double sum=0;
		List<GioHangCT> list = giohangCTDAO.findAllByIDGioHang(id);
		for (GioHangCT item : list) {
			if(item.getSach().getDiscount() == 0) {
				sum = sum + (item.getSach().getGia() * item.getSoLuong());

			}
			else {
				sum = sum + (item.getSach().getGia() * item.getSoLuong()*item.getSach().getDiscount());

			}
		}
		
		return sum;
	}

	@Override
	public void update(Long id, int qty) {
		// TODO Auto-generated method stub
		if (giohangCTDAO.existsById(id)) {
			GioHangCT gio = giohangCTDAO.findById(id).get();
			gio.setSoLuong(qty);
			giohangCTDAO.saveAndFlush(gio);
		}
	}
	
	

}
