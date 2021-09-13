/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medikal;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author erend
 */
public class Medikal extends Application {
        urun u;
        
        ListView listv=new ListView();
        
        GridPane gridPane=new GridPane(); 
        Button dosyakaydet=new Button("Dosya Kaydet"); 
        Button ekle=new Button("Ürün Ekle");
        Button sil=new Button("Ürün Sil");
        Button eklel=new Button("Ekle");
        Button guncelle=new Button("Ürünü Güncelle");
        
        
        TextField id=new TextField();
        TextField isim=new TextField();
        TextField adet=new TextField();
        TextField fiyat=new TextField();
        
        Label idl=new Label("İd Giriniz:");
        Label isiml=new Label("İsim Giriniz:");
        Label adetl=new Label("Adet Giriniz:");
        Label fiyatl=new Label("Fiyat Giriniz:");
        
        
        
        ComboBox urunler=new ComboBox();
        ComboBox malzeme=new ComboBox();
        ComboBox cihaz=new ComboBox();
        ComboBox ortopedik=new ComboBox();
        ComboBox medtekstil=new ComboBox();
        ComboBox medkimyasal=new ComboBox();
        
        depo depo1=new depo();
    
        public void sıfırla(){
        
        malzeme.setVisible(false);
        cihaz.setVisible(false);
        ortopedik.setVisible(false);
        medtekstil.setVisible(false);
        medkimyasal.setVisible(false);
        
        id.setVisible(false);
        isim.setVisible(false);
        adet.setVisible(false);
        fiyat.setVisible(false);
        
        idl.setVisible(false);
        isiml.setVisible(false);
        adetl.setVisible(false);
        fiyatl.setVisible(false);
        eklel.setVisible(false);
        guncelle.setVisible(false);
        
        id.setText("");
        isim.setText("");
        adet.setText("");
        fiyat.setText("");
        
        }
        public void goster(){
        id.setVisible(true);
        isim.setVisible(true);
        adet.setVisible(true);
        fiyat.setVisible(true);
        
        idl.setVisible(true);
        isiml.setVisible(true);
        adetl.setVisible(true);
        fiyatl.setVisible(true);
        guncelle.setVisible(true);
        eklel.setVisible(true);
        }
        public void doldur(){
            listv.getItems().clear();
        ArrayList<String> liste=depo1.listele();
        if(!liste.isEmpty()){
            for(String s:liste){
                listv.getItems().add(s);
            }
        }
        
        
        }
    @Override
    public void start(Stage stage) throws Exception {
        
        
        urunler.setVisible(false);
        sıfırla();
        doldur();
        urunler.setPromptText("Ürünler");
        urunler.getItems().addAll( 
                                    "Malzeme" ,
                                    "Cihaz");
        
        malzeme.setPromptText("Malzeme");
        malzeme.getItems().addAll( 
                                    "Ortopedik" ,
                                    "Medtekstil",
                                    "Medkimyasal");
        
        cihaz.setPromptText("Cihaz");
        cihaz.getItems().addAll( 
                                    "Kulak d. cihaz" ,
                                    "Şarj edilebilir cihaz",
                                    "Kulakiçi cihaz");
        ortopedik.setPromptText("Ortopedik");
        ortopedik.getItems().addAll( 
                                    "Koltuk Değneği" ,
                                    "Boyunluk",
                                    "Silikon Tabanlık");
        medtekstil.setPromptText("Med. Tekstil");
        medtekstil.getItems().addAll( 
                                    "Gözlük" ,
                                    "Koruyucu Önlük",
                                    "Koruyucu Maske");
        medkimyasal.setPromptText("Med. Kimyasal");
        medkimyasal.getItems().addAll( 
                                    "El Desenfektan" ,
                                    "Sabun",
                                    "EKG Jel");

        
        
        ekle.setOnAction(e->{
                GridPane.setConstraints(urunler,0,1);
                urunler.setVisible(true);
                sıfırla();
                urunler.getSelectionModel().clearSelection();
                malzeme.getSelectionModel().clearSelection();
                cihaz.getSelectionModel().clearSelection();
                ortopedik.getSelectionModel().clearSelection();
                medtekstil.getSelectionModel().clearSelection();
                medkimyasal.getSelectionModel().clearSelection();
                
        });
        eklel.setOnAction(e->{
            try{
                u.setId(Integer.parseInt(id.getText()));
                u.setIsim(isim.getText());
                u.setFiyat(Integer.parseInt(fiyat.getText()));
                u.setAdet(Integer.parseInt(adet.getText()));
                if(depo1.urun_ekle(u)){
                    doldur();
                    sıfırla();
                urunler.setVisible(false);
                Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Bilgilendirme");
                        alert.setHeaderText(null);
                        alert.setContentText("Ürün eklendi");

                         alert.showAndWait();
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Aynı id");
                        alert.setHeaderText(null);
                        alert.setContentText("Aynı id numaralı ürün mevcut.Lütfen farklı bir id numarası girin veya ürünü güncelleyin");

                         alert.showAndWait();
                }
            }catch(Exception a){
                Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText(null);
                        alert.setContentText("Girilen değerleri kontrol ediniz");
                        alert.showAndWait();
            }
                
                
        });
          dosyakaydet.setOnAction(e->{
            try{
                
                File file = new File("dosya.txt"); if(!file.exists())
                { file.createNewFile();} FileWriter fileWriter = new FileWriter(file,false);BufferedWriter bWriter = new BufferedWriter(fileWriter);
             ArrayList<String> lst = depo1.listele();
                for (Iterator<String> it = lst.iterator(); it.hasNext();) {
                    String sttr = it.next();
                    bWriter.write(sttr + System.lineSeparator());
                }
             bWriter.close();
            }catch(Exception a){
                
            }
                
                
        });
        sil.setOnAction(e->{
            
            try{
                 TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Sil");
                    dialog.setHeaderText("Ürün silme");
                    dialog.setContentText("Silmek istediğiniz id giriniz:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent() && depo1.urun_sil(Integer.parseInt(result.get()))){
                        doldur();
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Bilgilendirme");
                        alert.setHeaderText(null);
                        alert.setContentText("Ürün silindi");
                        alert.showAndWait();
                }
                    else{
                         Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Bilgilendirme");
                        alert.setHeaderText(null);
                        alert.setContentText("Girilien id numaralı ürün bulunamadı");
                        alert.showAndWait();
                    }
            }catch(Exception a){
                 Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText(null);
                        alert.setContentText("Girilen değerleri kontrol ediniz");
                        alert.showAndWait();
            }
               
                    
                    
        });
        guncelle.setOnAction(e->{
                try{
                    urunler.setVisible(false);
                u.setId(Integer.parseInt(id.getText()));
                u.setIsim(isim.getText());
                u.setFiyat(Integer.parseInt(fiyat.getText()));
                u.setAdet(Integer.parseInt(adet.getText()));
                if(depo1.urun_guncelle(u)){
            
                    doldur();
                    sıfırla();
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Bilgilendirme");
                        alert.setHeaderText(null);
                        alert.setContentText("Aynı id numaralı ürün güncellendi");
                        alert.showAndWait();
                }else{
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Bilgilendirme");
                        alert.setHeaderText(null);
                        alert.setContentText("Hata,ürün bulunamadı");
                        alert.showAndWait();
        }
                }catch(Exception a){
                     Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Hata");
                        alert.setHeaderText(null);
                        alert.setContentText("Girilen değerleri kontrol ediniz");
                        alert.showAndWait();
                }
            
            
            
                
        
        
        
        });
        EventHandler<ActionEvent> event = 
                  new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
               if(e.getSource()==urunler){//1
                        if(urunler.getSelectionModel().getSelectedIndex()==0){
                              GridPane.setConstraints(malzeme,1,1);
                              
                              malzeme.setVisible(true);
                              cihaz.setVisible(false);
                              ortopedik.setVisible(false);
                              medtekstil.setVisible(false);
                              medkimyasal.setVisible(false);
                              
                         }
                         else if(urunler.getSelectionModel().getSelectedIndex()==1){
                             GridPane.setConstraints(cihaz,1,1);
                             cihaz.setVisible(true);
                             malzeme.setVisible(false);
                             ortopedik.setVisible(false);
                             medtekstil.setVisible(false);
                             medkimyasal.setVisible(false);
                          }
               }
               else if(e.getSource()==malzeme){//2
                         if(malzeme.getSelectionModel().getSelectedIndex()==0){
                             GridPane.setConstraints(ortopedik,2,1);
                             cihaz.setVisible(false);
                             ortopedik.setVisible(true);
                             medtekstil.setVisible(false);
                             medkimyasal.setVisible(false);
                          }
                         else if(malzeme.getSelectionModel().getSelectedIndex()==1){
                             GridPane.setConstraints(medtekstil,2,1);
                             ortopedik.setVisible(false);
                             medtekstil.setVisible(true);
                             medkimyasal.setVisible(false);
                             cihaz.setVisible(false);
                         }
                         else if(malzeme.getSelectionModel().getSelectedIndex()==2){
                            GridPane.setConstraints(medkimyasal,2,1);
                            medkimyasal.setVisible(true);
                            ortopedik.setVisible(false);
                            medtekstil.setVisible(false);
                            cihaz.setVisible(false);
                             
                         }
               }
               else if(e.getSource()==cihaz){//3
                   
                         if(cihaz.getSelectionModel().getSelectedIndex()==0){
                             goster();
                             u=new kulak_d_cihaz();
                             isim.setText(cihaz.getSelectionModel().getSelectedItem().toString());
                         }
                        else if(cihaz.getSelectionModel().getSelectedIndex()==1){
                             goster();
                             u=new sarz_edilebilir_cihaz();
                             isim.setText(cihaz.getSelectionModel().getSelectedItem().toString());
                        }
                        else if(cihaz.getSelectionModel().getSelectedIndex()==2){
                            goster();
                             u=new kulak_ici_cihaz();
                             isim.setText(cihaz.getSelectionModel().getSelectedItem().toString());
                        }
               }
               else if(e.getSource()==ortopedik){//4
                   
                        if(ortopedik.getSelectionModel().getSelectedIndex()==0){
                             goster();
                             u=new koltuk_degnegi();
                             isim.setText(ortopedik.getSelectionModel().getSelectedItem().toString());
                         }
                        else if(ortopedik.getSelectionModel().getSelectedIndex()==1){
                             goster();
                             u=new boyunluk();
                             isim.setText(ortopedik.getSelectionModel().getSelectedItem().toString());
                        }
                        else if(ortopedik.getSelectionModel().getSelectedIndex()==2){
                            goster();
                             u=new silikon_taban();
                             isim.setText(ortopedik.getSelectionModel().getSelectedItem().toString());
                        }
                   
               }
               else if(e.getSource()==medtekstil){//5
                   
                        if(medtekstil.getSelectionModel().getSelectedIndex()==0){
                            goster();
                             u=new gozluk();
                             isim.setText(medtekstil.getSelectionModel().getSelectedItem().toString());
                         }
                        else if(medtekstil.getSelectionModel().getSelectedIndex()==1){
                             goster();
                             u=new koruyu_maske();
                             isim.setText(medtekstil.getSelectionModel().getSelectedItem().toString());
                        }
                        else if(medtekstil.getSelectionModel().getSelectedIndex()==2){
                            goster();
                             u=new koruyucu_onluk();
                             isim.setText(medtekstil.getSelectionModel().getSelectedItem().toString());
                        }
                   
               }
               else if(e.getSource()==medkimyasal){//6
                   
                        if(medkimyasal.getSelectionModel().getSelectedIndex()==0){
                             goster();
                             u=new el_dezenfektan();
                             isim.setText(medkimyasal.getSelectionModel().getSelectedItem().toString());
                         }
                        else if(medkimyasal.getSelectionModel().getSelectedIndex()==1){
                             goster();
                             u=new sabun();
                             isim.setText(medkimyasal.getSelectionModel().getSelectedItem().toString());
                        }
                        else if(medkimyasal.getSelectionModel().getSelectedIndex()==2){
                             goster();
                             u=new ekg_jel();
                             isim.setText(medkimyasal.getSelectionModel().getSelectedItem().toString());
                        }
                   
               }
               
            } 
        }; 
        urunler.setOnAction(event);
        malzeme.setOnAction(event);
        ortopedik.setOnAction(event);
        medtekstil.setOnAction(event);
        medkimyasal.setOnAction(event);
        cihaz.setOnAction(event);
        
        GridPane.setConstraints(ekle,0,0);
        GridPane.setConstraints(sil,1,0);
        GridPane.setConstraints(idl,0,3);
        GridPane.setConstraints(id,1,3);
        GridPane.setConstraints(isiml,0,4);
        GridPane.setConstraints(isim,1,4);
        GridPane.setConstraints(adetl,0,5);
        GridPane.setConstraints(adet,1,5);
        GridPane.setConstraints(fiyatl,0,6);
        GridPane.setConstraints(fiyat,1,6);
        GridPane.setConstraints(eklel,0,7);
        GridPane.setConstraints(guncelle,1,7);
        GridPane.setConstraints(dosyakaydet,1,8);
        
        gridPane.getChildren().addAll(guncelle,eklel,ekle,sil,urunler,malzeme,ortopedik,medtekstil,medkimyasal,cihaz,id,isim,adet,fiyat,idl,isiml,adetl,fiyatl,dosyakaydet);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20)); 
        FlowPane p=new FlowPane();
        p.setOrientation(Orientation.VERTICAL);
        p.setAlignment(Pos.TOP_CENTER);
        p.getChildren().add(gridPane);
        p.getChildren().add(listv);
        
        
        Scene scene = new Scene(p,800,600);
        
        stage.setTitle("Medikal");
        stage.setScene(scene);
        
        stage.show();
    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
