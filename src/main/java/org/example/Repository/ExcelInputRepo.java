package org.example.Repository;

import com.mysql.cj.result.Row;
import javafx.scene.control.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.McqModelClass;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class ExcelInputRepo implements ExcelInputRepoImpl{
    String url = "jdbc:mysql://localhost:3306/question";
    String username = "root";
    String password ="";
    @Override
    public List<McqModelClass>  excelSaveInDb(List<McqModelClass> modelClassList){
        List<McqModelClass> mcqModelClassList = new ArrayList<>();
        int size=modelClassList.size();
        String[] query=new String[size];
        for (int i=0;i<size;i++){
            query[i]="insert into mcq_que values ('"+modelClassList.get(i).getSr()+"'," +
                    "'"+modelClassList.get(i).getLevel()+"','"+modelClassList.get(i).getQue()+"','"+modelClassList.get(i).getOpn1()+"'," +
                    "'"+modelClassList.get(i).getOpn2()+"','"+modelClassList.get(i).getOpn3()+"','"+modelClassList.get(i).getOpn4()+"'," +
                    "'"+modelClassList.get(i).getOpn4()+"')";
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement st=connection.createStatement();
            for (int i=0;i<size;i++) {
                st.executeUpdate(query[i]);
            }
            ResultSet rs = st.executeQuery("select * from mcq_que");
            while(rs.next()){
                McqModelClass mcqModelClass = new McqModelClass();
                mcqModelClass.setSr(rs.getInt(1));
                mcqModelClass.setLevel(rs.getInt(2));
                mcqModelClass.setQue(rs.getString(3));
                mcqModelClass.setOpn1(rs.getString(4));
                mcqModelClass.setOpn2(rs.getString(5));
                mcqModelClass.setOpn3(rs.getString(6));
                mcqModelClass.setOpn4(rs.getString(7));
                mcqModelClass.setWopn(rs.getString(8));
                mcqModelClassList.add(mcqModelClass);
            }
        }catch (Exception e){
            System.out.println(e);
        }


        return mcqModelClassList;
    }

}
