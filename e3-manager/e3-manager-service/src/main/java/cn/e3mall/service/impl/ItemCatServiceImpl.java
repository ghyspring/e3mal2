package cn.e3mall.service.impl;


import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.dao.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 * @Author:Pineapple
 * @Date: 2018/4/10 21:12
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private TbItemCatMapper itemCatMapper;

    //根据parentId查询子节点列表
    @Override
    public List<EasyUITreeNode> getItemCatById(long parentId) {
        //设置查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        //把列表转换成EasyUITreeNode列表
        List<EasyUITreeNode> treeNodesList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(tbItemCat.getId());
            easyUITreeNode.setText(tbItemCat.getName());
            easyUITreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            treeNodesList.add(easyUITreeNode);
        }

        return treeNodesList;
    }
}
