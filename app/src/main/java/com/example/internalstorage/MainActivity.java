package com.example.internalstorage;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends Activity {
    float x1, x2, y1, y2;
    class Runner {
        public Runner(String webId, Integer raceNo, String category, String fName, String sName, String name) {
        }
    }

    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textmsg=(EditText)findViewById(R.id.editText1);
//        Button btn = (Button)findViewById(R.id.button3);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, DownloadRunners.class));
//            }
//        });
    }
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 < x2) {
                    Intent i = new Intent(MainActivity.this, DownloadRunners.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }


    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
        try {

//            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            FileOutputStream fileout = null;
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"IMRA/runners.xml");
            fileout = new FileOutputStream(file);
            fileout.write(textmsg.getText().toString().getBytes());
            fileout.close();
//            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
//            outputWriter.write(textmsg.getText().toString());
//            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        Toast.makeText(MainActivity.this,"readBtn", Toast.LENGTH_SHORT).show();
        try {
//            File directory = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//            String filepath = String.valueOf(new File(directory, "IMRA/runners.xml"));

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"IMRA/runners.xml");
            FileInputStream fileIn= new FileInputStream(file);
//            fileout = new FileOutputStream(file)
            InputStreamReader InputRead= new InputStreamReader(fileIn);
//            Toast.makeText(MainActivity.this,"read", Toast.LENGTH_SHORT).show();
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
////an instance of builder to parse the specified xml file
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(file);
//            doc.getDocumentElement().normalize();
//            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
//            NodeList nodeList = doc.getElementsByTagName("Runner");
//// nodeList is not iterable, so we are using for loop
//            List<Runner> runners =  new ArrayList<>();
            Toast.makeText(MainActivity.this,"Downloading", Toast.LENGTH_SHORT).show();
//            for (int itr = 0; itr < nodeList.getLength(); itr++)
//            {
//                Node node = nodeList.item(itr);
////                System.out.println("\nNode Name :" + node.getNodeName());
//                if (node.getNodeType() == Node.ELEMENT_NODE)
//                {
//                    Element eElement = (Element) node;
////                    System.out.println("Website ID: "+ eElement.getElementsByTagName("website_id").item(0).getTextContent());
//                    String webId = eElement.getElementsByTagName("website_id").item(0).getTextContent();
////                    System.out.println("Race Number: "+ eElement.getElementsByTagName("race_number").item(0).getTextContent());
//                    Integer raceNo = Integer.valueOf(eElement.getElementsByTagName("race_number").item(0).getTextContent());
////                    System.out.println("Category: "+ eElement.getElementsByTagName("category").item(0).getTextContent());
//                    String category = eElement.getElementsByTagName("category").item(0).getTextContent();
////                    System.out.println("First Name: "+ eElement.getElementsByTagName("first_name").item(0).getTextContent());
//                    String fName = eElement.getElementsByTagName("first_name").item(0).getTextContent();
////                    System.out.println("Last Name: "+ eElement.getElementsByTagName("SNAME").item(0).getTextContent());
//                    String sName = eElement.getElementsByTagName("SNAME").item(0).getTextContent();
////                    System.out.println("Name: "+ eElement.getElementsByTagName("NAME").item(0).getTextContent());
//                    String name = eElement.getElementsByTagName("NAME").item(0).getTextContent();
//                    runners.add(new Runner(webId, raceNo, category, fName, sName, name));
////                    System.out.println("\nNode Name :" + runners.toArray().length);
//                    Integer inputRaceNo = 666;
//                    if (inputRaceNo.equals(raceNo)) {
//                        System.out.println("Name: "+name);
//                        System.out.println("webId: "+webId);
//                        System.out.println("fName: "+fName);
//                        System.out.println("sName: "+sName);
//                        System.out.println("raceNo: "+raceNo);
////                        textmsg.setText(raceNo);
////                        Toast.makeText(MainActivity.this,raceNo, Toast.LENGTH_SHORT).show();
////                        System.out.println(runnerObj.get("surname"));
////                        System.out.println(runnerObj.get("firstname"));
////                        System.out.println(runnerObj.get("webId"));
//                    }
//                }
//
//            }

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            textmsg.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
//            String errorMsg;
//            errorMsg = e.printStackTrace();
            Toast.makeText(MainActivity.this,"error"+e, Toast.LENGTH_SHORT).show();
        }
    }
}