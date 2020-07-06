package com.xinwei.teachingplan.test;

import com.xinwei.teachingplan.vo.Menu;
import com.xinwei.teachingplan.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;

public class Test  {
    public static void main(String[] args) {

        List<Menu> sqlData=new ArrayList<>();
        sqlData.add(new Menu(1,"初一","xxx",-1));
        sqlData.add(new Menu(2,"高三","xxx",-1));
        sqlData.add(new Menu(4,"初二","xxx",-1));
        sqlData.add(new Menu(3,"物理","xxx",2));
        sqlData.add(new Menu(5,"化学","xxx",2));
        sqlData.add(new Menu(6,"英文","xxx",1));
        sqlData.add(new Menu(7,"语文","xxx",1));
        sqlData.add(new Menu(8,"数学","xxx",3));
        sqlData.add(new Menu(9,"约分","xxx",8));
        sqlData.add(new Menu(10,"作文","xxx",7));
        sqlData.add(new Menu(11,"力学","xxx",3));
        sqlData.add(new Menu(12,"有机化学","xxx",5));
        sqlData.add(new Menu(13,"电学","xxx",3));
        sqlData.add(new Menu(14,"英文作文","xxx",6));
        sqlData.add(new Menu(15,"平方","xxx",8));

        //存储非根节点
        List<MenuVo> tempList=new ArrayList<>();
        //存储最终的结果
        List<MenuVo> resultList=new ArrayList<>();
        //遍历数据库查询数据集合,如果父id==-1 代表根节点 添加到最终结果中,否则为非根节点添加到临时节点
        for (Menu menu : sqlData) {
            if(menu.getFatherId()==-1){
                resultList.add(new MenuVo(menu.getId(),menu.getName(),menu.getUrl(),menu.getFatherId(),new ArrayList<>()));
            }else{
                tempList.add(new MenuVo(menu.getId(),menu.getName(),menu.getUrl(),menu.getFatherId(),new ArrayList<>()));
            }
        }
        //遍历所有的根节点,通过根节点和非根节点集合,找到这个根节点的所有子节点
        for (MenuVo menuVo : resultList) {
            getChildNode(tempList,menuVo);
        }

        resultList.forEach(System.out::println);
    }


    public static  void getChildNode(List<MenuVo> tempList,MenuVo fatherNode  ){
        for (MenuVo menuVo : tempList) {
            //如果该节点的父id为传进来父节点的id 那么就为其子节点
            if(menuVo.getFatherId()==fatherNode.getId()){
                //添加到子节点数组
                fatherNode.getChildNode().add(menuVo);
                //递归调用 继续找该子节点  是否还有子节点
                getChildNode(tempList,menuVo);
            }
        }
    }


}