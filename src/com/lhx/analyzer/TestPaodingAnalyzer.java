package com.lhx.analyzer;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by xin on 15-1-19 下午9:25
 *
 * @project luceneDemo
 * @package com.lhx.analyzer
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */

public class TestPaodingAnalyzer {

    public static void main(String[] args) throws IOException {

        Analyzer analyzer = new PaodingAnalyzer();
        String input = "空格 a 空格" ;
        TokenStream ts = analyzer.tokenStream("", new StringReader(input));
        Token token = new Token();
        while( (token = ts.next(null)) != null){
            System.out.println(token.term());
        }
    }
}
