package medikal;

public class sarz_edilebilir_cihaz extends kvcihaz implements sarj_et {

	public sarz_edilebilir_cihaz(String isim, int id, int adet, int fiyat) {
		super(isim, id, adet, fiyat);
		// TODO Auto-generated constructor stub
	}

	
	public void sarj_et() {
		System.out.println("Cihaz �arj edildi");
		
	}
        public sarz_edilebilir_cihaz(){}

}
