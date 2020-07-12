package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.entity.MenuEntity;
import com.xinwei.teachingplan.mapper.PublicTeachMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PublicTeachImpl
 * @Author chengfei
 * @Date 2020/7/3 11:28
 * @Version 1.0
 * @Description: TODO
 **/
@Service("teach")
public class PublicTeachImpl implements PublicService {
    @Resource
    PublicTeachMapper publicTeachMapper;

    @Override
    public String getType() {
        return "teach";
    }

    @Override
    public List<String> grade() {
        return publicTeachMapper.grade();
    }

    @Override
    public List<String> course() {
        return publicTeachMapper.course();
    }

    @Override
    public List<String> year() {
        return publicTeachMapper.year();
    }

    public List<String> teachTopic() {
        return publicTeachMapper.teachTopic();
    }


    public List<MenuEntity> menuQuery(){
        List<MenuEntity> menus = publicTeachMapper.menuQuery();
        //存储非根节点
        List<MenuEntity> tempList=new ArrayList<>();
        //存储最终的结果
        List<MenuEntity> resultList=new ArrayList<>();
        //遍历数据库查询数据集合,如果父id==-1 代表根节点 添加到最终结果中,否则为非根节点添加到临时节点
        for (MenuEntity menu : menus) {
            if(menu !=null && menu.getFatherId() == -1){
                resultList.add(new MenuEntity(menu.getId(),menu.getName(),menu.getFatherId(),new ArrayList<MenuEntity>()));
            }else{
                tempList.add(new MenuEntity(menu.getId(),menu.getName(),menu.getFatherId(),new ArrayList<MenuEntity>()));
            }
        }
        //遍历所有的根节点,通过根节点和非根节点集合,找到这个根节点的所有子节点
        for (MenuEntity fatherNode : resultList) {
            getChildNode(tempList,fatherNode);
        }
        return resultList;
    }

    private void getChildNode(List<MenuEntity> tempList, MenuEntity fatherNode) {
        for (MenuEntity menuVo : tempList) {
            //如果该节点的父id为传进来父节点的id 那么就为其子节点
            if(menuVo.getFatherId().equals(fatherNode.getId())){
                //添加到子节点数组
                fatherNode.getChildNode().add(menuVo);
                //递归调用 继续找该子节点  是否还有子节点
                getChildNode(tempList,menuVo);
            }
        }
    }

    public Map<String, List<String>> synQueryTeach() {
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> topics = publicTeachMapper.teachTopic();
        List<String> lessonsTimes = publicTeachMapper.prepareLessonsTime();

        map.put("topic",topics);
        map.put("lessonsTime",lessonsTimes);
        return map;
    }

    public Map<String, List<String>> synQueryTeach(Long userId) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> topics = publicTeachMapper.teachPTopic(userId);
        List<String> lessonsTimes = publicTeachMapper.preparePLessonsTime(userId);
        map.put("topic",topics);
        map.put("lessonsTime",lessonsTimes);
        return map;
    }
}
