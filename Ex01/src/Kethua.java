import java.io.IOException;

class None{
	
}

abstract class Nguoi{
	protected String hoten, gioitinh, quequan;
	
	abstract void inThongTin() throws IOException, Exception;
	String getTen() {
		return hoten;
	}
	
}

class CanBo extends Nguoi{
	private String capham, chucvu;
	public CanBo(String hoten, String gioitinh, String quequan, String capham, String chucvu) {
		super.hoten = hoten;
		super.gioitinh = gioitinh;
		super.quequan = quequan;
		this.capham = capham;
		this.chucvu = chucvu;
	}
	void inThongTin() {
		System.out.println(super.hoten + " " + super.gioitinh + " " + super.quequan + " " + 
				capham + " " + chucvu);
	}
}

class SinhVien extends Nguoi{
	private int diem;
	public SinhVien(String hoten, String gioitinh, String quequan, int diem) {
		super.hoten = hoten;
		super.gioitinh = gioitinh;
		super.quequan = quequan;
		this.diem = diem;
	}
	void inThongTin() throws Exception {
		System.out.println(diem);
	}
}


public class Kethua {

	public Kethua() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nguoi[] arr = new Nguoi[100];
		
		SinhVien Dat = new SinhVien("Dat", "Nam", "BacGiang", 100);
		SinhVien Ngan = new SinhVien("Ngan", "Nu", "NgheAn", 99);
		CanBo Luong = new CanBo("Luong", "Nam", "BG", "General", "CucTruong");
		
	
		None none = new None();
		
		
		arr[0] = Dat;
		arr[1] = Ngan;
		arr[2] = Luong;
		for(int i = 0; i <= 2; i++) {
			System.out.println(arr[i].getClass());
		}
	}

}
