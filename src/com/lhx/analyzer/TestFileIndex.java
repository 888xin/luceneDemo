package com.lhx.analyzer;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;

/**
 * Created by xin on 15-1-19 下午10:22
 *
 * @project luceneDemo
 * @package com.lhx.analyzer
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestFileIndex {

    public static void main(String[] args) throws IOException {
        String dataDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata\\data";
        String indexDir = "C:\\Users\\xin\\Desktop\\csdn\\lucenedata" ;

        File[] files = new File(dataDir).listFiles() ;
        System.out.println(files.length);
        Analyzer analyzer = new PaodingAnalyzer();
        Directory dir = FSDirectory.getDirectory(indexDir) ;
        IndexWriter writer = new IndexWriter(dir, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);
        for (int i = 0; i < files.length; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            String line = "" ;
            FileInputStream is = new FileInputStream(files[i].getCanonicalPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"gbk"));
            line = reader.readLine();
            while (line != null){
                stringBuffer.append(line).append("\n");
                line = reader.readLine() ;
            }
            Document document = new Document() ;
            document.add(new Field("fileName", files[i].getName(), Field.Store.YES, Field.Index.ANALYZED));
            document.add(new Field("contents", stringBuffer.toString(), Field.Store.YES, Field.Index.ANALYZED));
            writer.addDocument(document);
            is.close();
            reader.close();
        }
        writer.close();
        dir.close();
    }
}
