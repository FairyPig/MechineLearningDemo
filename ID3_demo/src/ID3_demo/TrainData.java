package ID3_demo;

public class TrainData {
	public Object[] array_outside;
	public Object[] test_data;
	//定义构造函数，初始化对象时即调用
	public TrainData()
	{
		//初始化数组,数据以字符串的形式保存
		array_outside = new Object[]{
				/*
				new String[]{"qinglv","quansuo","zhuoxiang","qingxi","aoxian","yinghua","Yes"},
				new String[]{"wuhei","quansuo","chenmen","qingxi","aoxian","yinghua","Yes"},
				new String[]{"wuhei","quansuo","zhuoxiang","qingxi","aoxian","yinghua","Yes"},
				new String[]{"qinglv","quansuo","chenmen","qingxi","aoxian","yinghua","Yes"},
				new String[]{"qianbai","quansuo","zhuoxiang","qingxi","aoxian","yinghua","Yes"},
				new String[]{"qinglv","shaoquan","zhuoxiang","qingxi","shaoao","ruannian","Yes"},
				new String[]{"wuhei","shaoquan","zhuoxiang","shaohu","shaoao","ruannian","Yes"},
				new String[]{"wuhei","shaoquan","zhuoxiang","qingxi","shaoao","yinghua","Yes"},
				
				new String[]{"wuhei","shaoquan","chenmen","shaohu","shaoao","yinghua","No"},
				new String[]{"qinglv","yingting","qingcui","qingxi","pingtan","ruannian","No"},
				new String[]{"qianbai","yingting","qingcui","mohu","pingtan","yinghua","No"},
				new String[]{"qianbai","quansuo","zhuoxiang","mohu","pingtan","ruannian","No"},
				new String[]{"qinglv","shaoquan","zhuoxiang","shaohu","aoxian","yinghua","No"},
				new String[]{"qianbai","shaoquan","chenmen","shaohu","aoxian","yinghua","No"},
				new String[]{"wuhei","shaoquan","zhuoxiang","qingxi","shaoao","ruannian","No"},
				new String[]{"qianbai","quansuo","zhuoxiang","mohu","pingtan","yinghua","No"},
				new String[]{"qinglv","quansuo","chenmen","shaohu","shaoao","yinghua","No"},
				*/
				new String[]{"qinglv","quansuo","zhuoxiang","qingxi","aoxian","yinghua","Yes"},
				new String[]{"wuhei","quansuo","chenmen","qingxi","aoxian","yinghua","Yes"},
				new String[]{"wuhei","quansuo","zhuoxiang","qingxi","aoxian","yinghua","Yes"},
				new String[]{"qinglv","shaoquan","zhuoxiang","qingxi","shaoao","ruannian","Yes"},
				new String[]{"wuhei","shaoquan","zhuoxiang","shaohu","shaoao","ruannian","Yes"},
				
				new String[]{"qinglv","yingting","qingcui","qingxi","pingtan","ruannian","No"},
				new String[]{"qianbai","shaoquan","chenmen","shaohu","aoxian","yinghua","No"},
				new String[]{"wuhei","shaoquan","zhuoxiang","qingxi","shaoao","ruannian","No"},
				new String[]{"qianbai","quansuo","zhuoxiang","mohu","pingtan","yinghua","No"},
				new String[]{"qinglv","quansuo","chenmen","shaohu","shaoao","yinghua","No"},
				};
		test_data = new Object[]{
				new String[]{"qinglv","quansuo","chenmen","qingxi","aoxian","yinghua","Yes"},
				new String[]{"qianbai","quansuo","zhuoxiang","qingxi","aoxian","yinghua","Yes"},
				new String[]{"wuhei","shaoquan","zhuoxiang","qingxi","shaoao","yinghua","Yes"},
				
				new String[]{"wuhei","shaoquan","chenmen","shaohu","shaoao","yinghua","No"},
				new String[]{"qianbai","yingting","qingcui","mohu","pingtan","yinghua","No"},
				new String[]{"qinglv","shaoquan","zhuoxiang","shaohu","aoxian","yinghua","No"},
				new String[]{"qianbai","quansuo","zhuoxiang","mohu","pingtan","ruannian","No"},
				};
	}
}
