package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.Material;

import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {
    public void mapMaterial(String shipmentJson, BMWMapping bmwMapping) {
        
        Material material = new Material();
        List<Material> materialList = new ArrayList<>();
        String materialNumber = "", purchaseOrder = "", quantity = "", deliverNoteNumber = "";
        
        material.setMaterialNumber(materialNumber);
        material.setPurchaseOrder(purchaseOrder);
        material.setQuantity(quantity);
        material.setDeliverNoteNumber(deliverNoteNumber);
        
        materialList.add(material);
        bmwMapping.setMaterials(materialList);
    }
}