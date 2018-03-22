/**
 * 
 */
package kt2;

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
 * @param <T>
 *
 */
abstract class Nguoi implements Comparator<Nguoi>, Comparable<Nguoi>{
	private String ho, ten, ngaySinh;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Nguoi o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Nguoi o1, Nguoi o2) {
		// TODO Auto-generated method stub
		return 0;
	}



	/**
	 * @param ho
	 * @param ten
	 * @param ngaySinh
	 */
	public Nguoi(String ho, String ten, String ngaySinh) {
		this.ho = ho;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
	}

	

	/**
	 * @return the ho
	 */
	public String getHo() {
		return ho;
	}

	/**
	 * @param ho the ho to set
	 */
	public void setHo(String ho) {
		this.ho = ho;
	}

	/**
	 * @return the ten
	 */
	public String getTen() {
		return ten;
	}

	/**
	 * @param ten the ten to set
	 */
	public void setTen(String ten) {
		this.ten = ten;
	}

	/**
	 * @return the ngaySinh
	 */
	public String getNgaySinh() {
		return ngaySinh;
	}

	/**
	 * @param ngaySinh the ngaySinh to set
	 */
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
	
	
}

class SinhVien extends Nguoi implements Comparator<Nguoi>, Comparable<Nguoi>{
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
	public int compareTo(Nguoi o) {
		// TODO Auto-generated method stub
		if (o instanceof SinhVien) {
			SinhVien sv = (SinhVien) o;
			return Double.compare(sv.diem, this.diem); 
		}
		return 0;
	}

	@Override
	public int compare(Nguoi o1, Nguoi o2) {
		// TODO Auto-generated method stub
		if (o1 instanceof SinhVien) {
			SinhVien sv = (SinhVien) o1;
			SinhVien sv2 = (SinhVien) o2;
			if (sv2.diem > sv.diem) return 1;
			else if (sv2.diem < sv.diem) return -1;
			else return 0;
		}
		return 0;
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
		
		DanhSach danhSach = new DanhSach(list);
		danhSach.sapxep();
		//Collections.sort(list);
		for (SinhVien e : danhSach.list) {
			System.out.println(e.getHo() + " " + e.getTen() + " " + Double.toString(e.getDiem()));
		}
	}

}
