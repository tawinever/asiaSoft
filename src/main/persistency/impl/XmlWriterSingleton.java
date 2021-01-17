package src.main.persistency.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import src.main.entity.NumContainer;
import src.main.entity.SmartNums;
import src.main.exception.SizeOverflowException;
import src.main.persistency.WriterAbstract;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class XmlWriterSingleton extends WriterAbstract {
    private static XmlWriterSingleton writer = null;
    public static final String fileDir = "queue";
    public static final Float max_fie_size_kb = 1F;


    private XmlWriterSingleton() throws Exception {
        File dir = new File(fileDir);
        if (dir.mkdir() || dir.exists()) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Couldn't create a directory: " + fileDir);
            throw new Exception("Couldn't create a directory " + fileDir);
        }
    }

    public static XmlWriterSingleton getInstance() {
        if (writer == null) {
            try {
                writer = new XmlWriterSingleton();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return writer;
    }

    @Override
    public void logSmartNums(NumContainer<SmartNums> numContainer) {
        Document dom;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.newDocument();

            Element root = dom.createElement("NumContainer");

            // create data elements and place them under root


            numContainer.nums.forEach(x -> {
                Element num = dom.createElement("SmartNum");
                Element val = dom.createElement( "Value");
                Element isEven = dom.createElement("SignEven");

                val.appendChild(dom.createTextNode(String.valueOf(x.getVal())));
                isEven.appendChild(dom.createTextNode(String.valueOf(x.isEven())));

                num.appendChild(val);
                num.appendChild(isEven);

                root.appendChild(num);
            });

            dom.appendChild(root);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                // generating file
                long curTimestamp = new Date().getTime();
                File xml = new File(fileDir + "/smartnums_" + curTimestamp + ".xml");
                xml.createNewFile();



                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xml)));

                float fileSize = xml.length();
                System.out.println(fileSize / 1024F + " kb");

                if (fileSize / 1024F > max_fie_size_kb) {
                    xml.delete();
                    throw new SizeOverflowException("XML file cannot be bigger than " + max_fie_size_kb + " kb");
                }



            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            } catch (TransformerException | SizeOverflowException e) {
                e.printStackTrace();
            }
        } catch ( ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        }
    }
}
