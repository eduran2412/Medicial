package medikal;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class depo {

	ArrayList<urun> urunler=new ArrayList<urun>();
	
	
	public boolean urun_ekle(urun u){
            boolean durum=true;
                if(urunler.isEmpty()){
                    urunler.add(u);
                }
                else{
                    
                    
                    ListIterator<urun> i=urunler.listIterator();
                    while (i.hasNext()) {
                    int num=i.next().getId();
                    if(u.getId()==num){
                        durum=false;
                        
                    }
                        
                    }
                    
                    if(durum){
                        urunler.add(u);
                    }
	      }
                
                
                
                
		
                return durum;
		
		
		
	}
        public boolean urun_guncelle(urun u){
            boolean durum=false;
            int num=0;
                if(urunler.isEmpty()){
                    urunler.add(u);
                    durum=true;
                }
                else{
                    ListIterator<urun> i=urunler.listIterator();
                    while (i.hasNext()) {
                        num=i.next().getId();
                        if(num==u.getId()) {
                        
                            
                           
                            durum=true;
                            i.remove();
                            break;
                    
                    
                        }
                        
                    }
                    if(durum){
                         urun urn=new urun(u.getIsim(),num,u.getAdet(),u.getFiyat());
                         urunler.add(urn);
                    }
                
                
                }
		
                return durum;
		
		
		
	}
        
	public boolean urun_sil(int id){
            boolean durum=false;
		Iterator<urun> i=urunler.iterator();
		while (i.hasNext()) {
	         int num=i.next().getId();
	         if(num==id) {
	            i.remove();
                    durum=true;
	            break;
	         }
	      }
		
		return durum;
		
	}
	
	public ArrayList listele(){
		ArrayList<String> listeleme=new ArrayList<String>();
                String toplam_string;
                for(urun u:urunler){
                    toplam_string=u.getId()+" "+u.getIsim()+" "+u.getAdet()+" "+u.getFiyat();
                    listeleme.add(toplam_string);
                }
                
                
                return listeleme;
	}
	
	
	
	
}
