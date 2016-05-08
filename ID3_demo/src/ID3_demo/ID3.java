package ID3_demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ID3 {
	TreeNode root;
	Object[] training_data;
	Object[] test_data;
	int y,attribute_num,test_num;
	String[][] attributes;
	String[] results;
	public ID3(){
		init();
		TrainData t = new TrainData();
		root = new TreeNode();
		training_data = t.array_outside;
		test_data = t.test_data;
	}
	public void cutBranch(){
		System.out.println("=====beforeCutBranch====="+"  "+getAccuracy());
		double bf=0.0,af=0.0;
		TreeNode node;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		Stack<TreeNode> p = new Stack<TreeNode>();
		q.add(root);
		p.add(root);
		while(!q.isEmpty()){
			node = q.poll();
			if(node.isLeaft){
			}else{
				p.add(node);
				for(int i=0;i<node.childNodes.length;i++){
					q.add(node.childNodes[i]);
				}
			}
		}
		System.out.println("p.size:  "+p.size());
		while(!p.isEmpty()){
			node = p.pop();
			bf = getAccuracy();
			node.isLeaft = true;
			af = getAccuracy();
			if(bf > af){
				node.isLeaft = false;
			}
			System.out.println("node: "+node.attribute_id+"  bf: "+bf+" af: "+af);
		}
		System.out.println("=====afterCutBranch====="+"  "+getAccuracy());
	}
	public double getAccuracy(){
		double accuracy = 0.0;
		int good=0,bad=0;
		String rlt;
		for(int i=0;i<test_num;i++){
			rlt = testData((String[])(test_data[i]));
			if(rlt.equals(((String[])(test_data[i]))[attribute_num])){
				good++;
			}else{
				bad++;
			}
		}
		//System.out.println("good: "+good+" bad: "+bad);
		accuracy = (double)good/(double)(good+bad);
		return accuracy;
	}
	public String testData(String[] data){
		int i;
		String result = "";
		boolean flag = false;
		TreeNode t = root;
		while(true){
			for(i=0;i<attributes[t.attribute_id].length;i++){
				if(attributes[t.attribute_id][i].equals(data[t.attribute_id])){
					if(!t.isLeaft)
						t = t.childNodes[i];
					else{
						flag = true;
						result = t.type;
						//System.out.println("判断结果："+t.type);
						break;
					}
				}
			}
			if(flag)
				break;
		}
		return result;
	}
	public void printTree(TreeNode root){
		Queue<TreeNode> s = new LinkedList<TreeNode>();
		TreeNode t;
		s.add(root);
		while(!s.isEmpty()){
			t = s.poll();
			if(t!=null){
				if(t.isLeaft){
					System.out.println("叶子节点：     "+t.type + " 父属性  "+t.parent.attribute_id);
				}else{
					if(t.parent!=null)
					System.out.println("不是叶子节点：       "+t.attribute_id + " 父属性  "+t.parent.attribute_id);
					else
						System.out.println("不是叶子节点：       "+t.attribute_id);
					for(int i=0;i<t.childNodes.length;i++){
						System.out.println(t.attributes[i]);
						s.add(t.childNodes[i]);
					}
				}
			}
		}
	}
	public void TreeGenerate(ArrayList<Integer>D,ArrayList<Integer>a,TreeNode father){
		boolean flag = true;
		int i,j,selectnum;
		if(father == root){
			root.type = getMostInD(D);
		}
		for(i=1;i<D.size();i++)
		{
			if(((String[])(training_data[D.get(i)]))[attribute_num].equals(((String[])(training_data[D.get(0)]))[attribute_num])){
			}else{
				flag = false;
			}
		}
		//D中所有样本全属于同一类别C
		if(flag){
			//将node标记为C类叶节点，return
			father.isLeaft = true;
			father.type = ((String[])(training_data[D.get(0)]))[attribute_num];
			return;
		}
		if(a.size() == 0 || isSameD(D,a)){
			//将 node标记为叶节点，其类别标记为D中样本数最多的类；return
			father.isLeaft = true;
			father.type = getMostInD(D);
			return;
		}
		selectnum = selectAttr(D,a);
		ArrayList<Integer> av = new ArrayList<Integer>();
		for(i=0;i<a.size();i++){
			if(a.get(i)!=selectnum){
				av.add(a.get(i));
			}
		}
		father.attribute_id = selectnum;
		father.isLeaft = false;
		int len = attributes[selectnum].length;
		father.childNodes = new TreeNode[len];
		father.attributes = new String[len];
		for(i=0;i<len;i++){
			TreeNode tree = new TreeNode();
			father.childNodes[i] = tree;
			father.attributes[i] = attributes[selectnum][i];
			tree.parent = father;
			tree.isLeaft = false;
			//为node生成一个分支；令Dv表示D中在a*上取值为av*的样本子集
			ArrayList<Integer>Dv = new ArrayList<Integer>();
			for(j=0;j<D.size();j++){
				if(((String[])(training_data[D.get(j)]))[selectnum].equals(attributes[selectnum][i])){
					Dv.add(D.get(j));
				}
			}
			if(Dv.isEmpty()){
				//将分支节点标记为叶节点，其类别标记为D中样本最多的类；return;
				tree.isLeaft = true;
				tree.type = getMostInD(D);
			}else{
				//递归调用TreeGenerate()
				tree.type = getMostInD(Dv);
				TreeGenerate(Dv,av,tree);
			}
		}
	}
	public String getMostInD(ArrayList<Integer>D){
		String result = "";
		int ans = -100,i,j;
		for(i=0;i<results.length;i++){
			int tmp = 0;
			for(j=0;j<D.size();j++){
				if(((String[])(training_data[D.get(j)]))[attribute_num].equals(results[i])){
					tmp++;
				}
			}
			if(tmp > ans){
				result = results[i];
			}
		}
		return result;
	}
	public boolean isSameD(ArrayList<Integer>D,ArrayList<Integer>a){
		boolean flag = true;
		int i,j;
		for(i=0;i<a.size();i++){
			for(j=1;j<D.size();j++){
				if(((String[])(training_data[D.get(j)]))[a.get(i)].equals(((String[])(training_data[D.get(0)]))[a.get(i)])){	
				}else{
					flag = false;
					break;
				}
			}
			if(!flag)
				break;
		}
		return flag;
	}
	public int selectAttr(ArrayList<Integer>D,ArrayList<Integer>a){
		int max_a = 0,i;
		double max = -1000000.0;
		for(i=0;i<a.size();i++){
			double tmp = getGain(D,a.get(i));
			if(tmp >= max){
				max = tmp;
				max_a = a.get(i);
			}
		}
		return max_a;
	}
	//a_num为从0~5
	public double getGain(ArrayList<Integer> D,int a_num)
	{
		double gain = 0.0,ent = 0.0;
		ArrayList<Integer> di = new ArrayList<Integer>();
		int len = attributes[a_num].length;
		int i,j,k;
		int[] ans = new int[len];
		for(i=0;i<len;i++)
			ans[i] = 0;
		for(i=0;i<len;i++)
		{
			for(j=0;j<D.size();j++){
				if(((String[])training_data[D.get(j)])[a_num].equals(attributes[a_num][i])){
					di.add(j);
				}
			}
			ent += (double)di.size()/D.size()*(getEnt(di));
			di.clear();
		}
		gain = getEnt(D) - ent;
		return gain;
	}
	public double getEnt(ArrayList<Integer> D)
	{
		double ent = 0.0;
		int[] ans = new int[y];
		int total = 0;
		for(int i=0;i<y;i++)
			ans[i] = 0;
		for(int i=0;i<D.size();i++)
		{
			for(int j=0;j<y;j++)
			{
				if(((String[])training_data[D.get(i)])[attribute_num].equals(results[j])){
					ans[j]++;
					total ++;
					break;
				}
			}
		}
		for(int i=0;i<y;i++)
		{
			if(ans[i]!=0)
				ent += (double)ans[i]/(double)total * (Math.log((double)ans[i]/total)/Math.log(2));
		}
		ent = -ent;
		return ent;
	}
	public void init(){
		y = 2;
		test_num = 7;
		results = new String[]{"Yes","No"};
		
		attribute_num = 6;
		attributes = new String[attribute_num][];
		attributes[0] = new String[]{
			"qinglv","wuhei","qianbai"
		};
		attributes[1] = new String[]{
			"quansuo","shaoquan","yingting"
		};
		attributes[2] = new String[]{
			"zhuoxiang","chenmen","qingcui"
		};
		attributes[3] = new String[]{
				"qingxi","shaohu","mohu"
		};
		attributes[4] = new String[]{
				"aoxian","shaoao","pingtan"
		};
		attributes[5] = new String[]{
				"yinghua","ruannian"
		};
	}
	public static void main(String[] args){
		ID3 id3 = new ID3();
		ArrayList<Integer> d = new ArrayList<Integer>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		for(int i = 0;i<10;i++){
			d.add(i);
		}
		for(int i = 0;i<6;i++){
			a.add(i);
		}
		id3.TreeGenerate(d, a, id3.root);
		id3.printTree(id3.root);
		id3.cutBranch();
	}
}
