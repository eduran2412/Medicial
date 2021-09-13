package medikal;

public class urun {
	private String isim;
	private int id;
	private int adet;
	private int fiyat;
	
	public urun(String isim,int id,int adet,int fiyat){
		this.isim=isim;
		this.id=id;
		this.adet=adet;
		this.fiyat=fiyat;
		
		
	}
        public urun(){}
	

	

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public int getFiyat() {
		return fiyat;
	}

	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	
	
	public void listele(){
		System.out.println(getId()+" "+getIsim()+" "+getFiyat()+" "+getAdet());
	}
	
	

}
