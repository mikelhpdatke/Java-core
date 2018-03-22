/**
 * 
 */
package kt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.script.Compilable;

/**
 * @author luong
 *
 */
abstract class Nguoi{
	private String ho, ten, ngaySinh;

	
	public Nguoi(String ho, String ten, String ngaySinh) {
		this.ho = ho;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
	}

	
	public String getHo() {
		return ho;
	}

	
	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	
	public void setTen(String ten) {
		this.ten = ten;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder()
				.append(ho)
				.append(" ")
				.append(ten)
				.append(" ")
				.append(ngaySinh)
				.toString();
	}
	
	////////////
	
	
	
}

class SinhVien extends Nguoi implements Comparator<SinhVien>, Comparable<SinhVien>{
	private double diem;
	public SinhVien(String ho, String ten, String ngaySinh, double diem) {
		super(ho, ten, ngaySinh);
		this.diem = diem;
		// TODO Auto-generated constructor stub
	}
	
	public double getDiem() {
		return diem;
	}
	
	public void setDiem(int diem) {
		this.diem = diem;
	}



	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().
				append(" ").
				append(super.toString()).
				append(" ").
				append(Double.toString((this.diem))).
				toString();
	}

	@Override
	public int compareTo(SinhVien o) {
		// TODO Auto-generated method stub
		return Double.compare(o.diem, this.diem);
	}

	@Override
	public int compare(SinhVien o1, SinhVien o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
	
	
}

class DanhSach {
	ArrayList<SinhVien> list;
	
	public DanhSach(ArrayList<SinhVien> list) {
		this.list = list;
		
	}
	
	void them(SinhVien e){
		list.add(e);
	}
	
	void xoa(int i) {
		list.remove(i);
	}
	
	void sapxep() {
		Collections.sort(list);
	}
}


public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\luong\\eclipse-workspace\\kt1\\src\\kt1\\sinhvien.txt");
		
		Scanner scanner = new Scanner(file);
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		while (scanner.hasNextLine()) {
			String ho = (String) scanner.next();
			String ten = (String) scanner.next();
			double diem = scanner.nextDouble();
			
			list.add(new SinhVien(ho, ten, "none", diem));
		}
		
		//DanhSach danhSach = new DanhSach(list);
		//danhSach.sapxep();
		Collections.sort(list);
		for (SinhVien e : list) {
			System.out.println(e.getHo() + " " + e.getTen() + " " + Double.toString(e.getDiem()));
		}
	}

}
