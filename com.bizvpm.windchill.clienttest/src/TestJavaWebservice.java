import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.bizvpm.windchill.ClientDataObjectConverter;
import com.bizvpm.windchill.DataObject;
import com.bizvpm.windchill.DataSet;
import com.bizvpm.windchill.Exception_Exception;
import com.bizvpm.windchill.KeyValuePair;
import com.bizvpm.windchill.TegWCService;
import com.bizvpm.windchill.TegWCServiceService;


public class TestJavaWebservice {

	public static void main(String[] args) throws MalformedURLException, Exception_Exception {
		TegWCServiceService service = new TegWCServiceService(new URL(
				"http://127.0.0.1:8080/windchillservice/TegWCService?wsdl"));
		TegWCService wcservice = service.getTegWCServicePort();
		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤ͨ��WTPart�ı����ȡWTPart�Ļ�����Ϣ:");
//		testGetWTPartBasicInfoByNumberList(wcservice);
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("��֤ͨ��WTPart��OUID��ȡWTPart�Ļ�����Ϣ:");	
		testGetWTPartBasicInfoByOuidList(wcservice);
		
	}	
		
	private static void testGetWTPartBasicInfoByNumberList(TegWCService wcservice) throws Exception_Exception{
		DataObject inputDTO = new DataObject();
		List<DataObject> dtoList = new ArrayList<DataObject>();
		DataObject exchangeDTO = new DataObject();
		exchangeDTO.setStringValue("PLG.H326.001.000");
		dtoList.add(exchangeDTO);
		inputDTO.setListValue(dtoList); 
		System.out.println("��֤����--------->>>>>>>>>>:");
		DataObject outputDTO =  wcservice.getWTPartBasicInfoByNumberList(inputDTO);
		
		dtoPrint(outputDTO);

		DataObject exchangeDTO1 = new DataObject();
		exchangeDTO1.setStringValue("PLG.CS7.002.000");
		dtoList.add(exchangeDTO1);
		DataObject exchangeDTO2 = new DataObject();
		exchangeDTO2.setStringValue("PLG.H326.151.000");
		dtoList.add(exchangeDTO2);
		inputDTO.setListValue(dtoList); 
		System.out.println("��֤����--------->>>>>>>>>>:");
		outputDTO =  wcservice.getWTPartBasicInfoByNumberList(inputDTO);
		dtoPrint(outputDTO);
	}
	
	private static void testGetWTPartBasicInfoByOuidList(TegWCService wcservice) throws Exception_Exception{
		DataObject inputDTO = new DataObject();
		List<DataObject> dtoList = new ArrayList<DataObject>();
		DataObject exchangeDTO = new DataObject();
		exchangeDTO.setStringValue("25682790");
		dtoList.add(exchangeDTO);
		inputDTO.setListValue(dtoList); 
		System.out.println("��֤����--------->>>>>>>>>>:");
		DataObject outputDTO =  wcservice.getWTPartDetailInfoByOuidList(inputDTO);
		
		dtoPrint(outputDTO);

		DataObject exchangeDTO1 = new DataObject();
		exchangeDTO1.setStringValue("26096238");
		dtoList.add(exchangeDTO1);
		DataObject exchangeDTO2 = new DataObject();
		exchangeDTO2.setStringValue("25656164");
		dtoList.add(exchangeDTO2);
		inputDTO.setListValue(dtoList); 
		System.out.println("��֤����--------->>>>>>>>>>:");
		outputDTO =  wcservice.getWTPartDetailInfoByOuidList(inputDTO);
		dtoPrint(outputDTO);
	}
	
	private static void dtoPrint(DataObject printDTO){
		System.out.println("{");
		ClientDataObjectConverter dc = new ClientDataObjectConverter();
		DataSet levelOneSet = printDTO.getMapValue();
		List<KeyValuePair> values1 = levelOneSet.getValues();
		for (KeyValuePair p1 : values1) {
			System.out.println(p1.getKey() + "----->>>>>");	
//			String keyLevel1 = p1.getKey();
//			Object objLevel1 = p1.getValue();
			if (p1.getValue() != null){
				List<KeyValuePair> values2 = (((DataObject) p1.getValue()).getMapValue()).getValues();
				for (KeyValuePair p2 : values2) {
					System.out.print(p2.getKey() + ":" + dc.getValue((DataObject)p2.getValue()) + ",");
				}				
			}
			System.out.println();
		}
		System.out.println("}");		
	}
		
//		
//		DataSet attrNameSet = new DataSet();
//		attrNameSet.setValue("com.plm.hyth.UnitPartsType", "");
//		attrNameSet.setValue("com.plm.hyth.UnitPartsType", "");	//wtpart:�㲿�������
//		attrNameSet.setValue("com.plm.hyth.IsImpPart", "");	//wtpart:�Ƿ���ؼ�
//		attrNameSet.setValue("com.plm.hyth.ERPPartNumber", "");	//wtpart:ERP����
//		
//		attrNameSet.setValue("com.plm.hyth.documentType", "");	//�ĵ�С��
//		attrNameSet.setValue("com.plm.hyth.Securityr", "");	//�ĵ����ܼ���
//		
//		attrNameSet.setValue("com.plm.hyth.MaterialLong", "");	//WTPartUsageLink:���ϳߴ�-��
//		attrNameSet.setValue("com.plm.hyth.MaterialHeight", "");	//WTPartUsageLink:���ϳߴ�-��
//		
//		attrNameSet.setValue("aaa", "");	//����
//
//		
//		ClientDataObjectConverter dc = new ClientDataObjectConverter();
//		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤WTPart��ϸ��Ϣ���������ԣ�:");
//		DataSet resultPart = wcservice.getObjDetailInfo("PLG.H326.001.000" , "WTPart" , attrNameSet);
//		System.out.print("{");
//			List<KeyValuePair> valuesPart = resultPart.getValues();
//			for (KeyValuePair p : valuesPart) {
//				DataObject dto = p.getValue();
//				Object value = dc.getValue(dto);
//				System.out.print(p.getKey() + ":" + value + ",");
//			}
//		System.out.print("}");
//		System.out.println();
//		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤WTDocument��ϸ��Ϣ���������ԣ�:");
//		DataSet resultDocument = wcservice.getObjDetailInfo("DRW00000304" , "WTDocument" , attrNameSet);		
//		System.out.print("{");
//			List<KeyValuePair> valuesDocument = resultDocument.getValues();
//			for (KeyValuePair p : valuesDocument) {
//				DataObject dto = p.getValue();
//				Object value = dc.getValue(dto);
//				System.out.print(p.getKey() + ":" + value + ",");
//			}
//		System.out.print("}");
//		System.out.println();
//		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤EPM��ϸ��Ϣ���������ԣ�:");
//		DataSet resultEPM = wcservice.getObjDetailInfo("PLG-H326-001-000.ASM" , "EPMDocument" , attrNameSet);		
//		System.out.print("{");
//			List<KeyValuePair> valuesEPM = resultEPM.getValues();
//			for (KeyValuePair p : valuesEPM) {
//				DataObject dto = p.getValue();
//				Object value = dc.getValue(dto);
//				System.out.print(p.getKey() + ":" + value + ",");
//			}
//		System.out.print("}");
//		System.out.println();
//		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤�㲿��˵���ĵ���ϸ��Ϣ���������ԣ�:");
//		DataObject dtoRelateDescribDOC = wcservice.getPartRelatdObjList("PLG.H326.001.000" , 
//				"WTPartDescribeLink" , attrNameSet);		
//		List<DataObject> dsListRelateDescribDOC = dtoRelateDescribDOC.getListValue();
//		for (int i=0 ; i<dsListRelateDescribDOC.size() ;i++){
//			System.out.print("{");
//			DataObject dto = dsListRelateDescribDOC.get(i);
//			DataSet ds = dto.getMapValue();
//			List<KeyValuePair> values = ds.getValues();
//			for (KeyValuePair p : values) {
//				DataObject dtoValue = p.getValue();
//				Object value = dc.getValue(dtoValue);
//				System.out.print(p.getKey() + ":" + value + ",");
//			}
//			System.out.print("}");
//			System.out.println();
//		}
//
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤�㲿���ο��ĵ���ϸ��Ϣ���������ԣ�:");
//		DataObject dtoRelateRefDOC = wcservice.getPartRelatdObjList("PLG.H326.001.000" , 
//				"WTPartReferenceLink" , attrNameSet);		
//		List<DataObject> dsListRelateRefDOC = dtoRelateRefDOC.getListValue();
//		for (int i=0 ; i<dsListRelateRefDOC.size() ;i++){
//			System.out.print("{");
//			DataObject dto = dsListRelateRefDOC.get(i);
//			DataSet ds = dto.getMapValue();
//			List<KeyValuePair> values = ds.getValues();
//			for (KeyValuePair p : values) {
//				DataObject dtoValue = p.getValue();
//				Object value = dc.getValue(dtoValue);
//				System.out.print(p.getKey() + ":" + value + ",");
//			}
//			System.out.print("}");
//			System.out.println();
//		}
//		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤�㲿��˵��EPM��ϸ��Ϣ���������ԣ�:");
//		DataObject dtoEPMDesDOC = wcservice.getPartRelatdObjList("PLG.H326.001.000" , 
//				"EPMDescribeLink" , attrNameSet);		
//		List<DataObject> dsListEPMDesDOC = dtoEPMDesDOC.getListValue();
//		for (int i=0 ; i<dsListEPMDesDOC.size() ;i++){
//			System.out.print("{");
//			DataObject dto = dsListEPMDesDOC.get(i);
//			DataSet ds = dto.getMapValue();
//			List<KeyValuePair> values = ds.getValues();
//			for (KeyValuePair p : values) {
//				DataObject dtoValue = p.getValue();
//				Object value = dc.getValue(dtoValue);
//				System.out.print(p.getKey() + ":" + value + ",");
//			}
//			System.out.print("}");
//			System.out.println();
//		}
		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("��֤�㲿���ο�EPM��ϸ��Ϣ���������ԣ�:");
//		DataObject dtoEPMRefDOC = wcservice.getPartRelatdObjList("PLG.H326.001.000" , 
//				"EPMReferenceLink" , attrNameSet);		
//		List<DataObject> dsListEPMRefDOC = dtoEPMRefDOC.getListValue();
//		for (int i=0 ; i<dsListEPMRefDOC.size() ;i++){
//			System.out.print("{");
//			DataObject dto = dsListEPMRefDOC.get(i);
//			DataSet ds = dto.getMapValue();
//			List<KeyValuePair> values = ds.getValues();
//			for (KeyValuePair p : values) {
//				DataObject dtoValue = p.getValue();
//				Object value = dc.getValue(dtoValue);
//				System.out.print(p.getKey() + ":" + value + ",");
//			}
//			System.out.print("}");
//			System.out.println();
//		}
		

	
	

}
