package com.ghf.core.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneUtils {
	private static final Logger log = Logger.getLogger(LuceneUtils.class);
	
	private static Directory directory; // ������Ŀ¼
	private static Analyzer analyzer; // �ִ���
	private static Version version = Version.LUCENE_47;
	private static IndexWriterConfig indexWriterConfig;
	private static IndexWriter indexWriter;
	
	static {
		try {
			//����Ӧ�Ƕ�ȡ�����ļ��õ���������Ŀ¼
			directory = FSDirectory.open(new File("./indexDir"));
			analyzer = new StandardAnalyzer(Version.LUCENE_47);
			indexWriterConfig = new IndexWriterConfig(version, analyzer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * ��ȡȫ��Ψһ��IndexWriter����
     * 
     * @return
     */
    public static IndexWriter getIndexWriter() {
        // �ڵ�һ��ʹ��IndexWriter�ǽ��г�ʼ��
        if (indexWriter == null) {
            synchronized (LuceneUtils.class) { // ע���̰߳�ȫ����
                if (indexWriter == null) {
                    try {
                        indexWriter = new IndexWriter(directory, indexWriterConfig);
                        System.out.println("=== �Ѿ���ʼ�� IndexWriter ===");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            // ָ��һ�δ��룬����JVM�˳�֮ǰִ�С�
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        indexWriter.close();
                        System.out.println("=== �Ѿ��ر� IndexWriter ===");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        return indexWriter;
    }
	
	
}
