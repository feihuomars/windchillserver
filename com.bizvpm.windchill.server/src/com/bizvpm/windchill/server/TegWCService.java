package com.bizvpm.windchill.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.bizvpm.windchill.server.sqldb.utility.SQLResult;
import com.bizvpm.windchill.server.sqldb.utility.SQLRow;
import com.bizvpm.windchill.server.sqldb.utility.SQLUtil;
import com.bizvpm.windchill.server.wstype.DataObject;
import com.bizvpm.windchill.server.wstype.DataSet;
import com.bizvpm.windchill.server.wstype.KeyValuePair;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TegWCService {

	private String connectString = "tegwindchill";
	
	@WebMethod
	public DataObject getWTPartBasicInfoByNumberList(
			@WebParam(name = "numberList", partName = "numberList") DataObject numberList
			) throws Exception{
		DataObject dto = new DataObject();
		
		List<DataObject> inputList =  numberList.getListValue();
		
		StringBuffer sql = new StringBuffer();
		if (inputList.size() == 1){
			String number = inputList.get(0).getStringValue();
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpartmaster.wtpartnumber = '" + number+"' ");
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			sql.append(" and wtpart.latestiterationinfo = 1");
			sql.append(" order by wtpart.createstampa2 desc");
			
			DataSet oneSet = getDSByPartSql(sql.toString());
			DataSet returnSet = new  DataSet();
			returnSet.setValue(number, oneSet);
			dto.setMapValue(returnSet);
			
		}else{
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpartmaster.wtpartnumber " + getInClause(inputList));
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			sql.append(" and wtpart.latestiterationinfo = 1");
			sql.append(" order by wtpart.createstampa2 desc");
			
			dto = getDTOByPartSql(sql.toString());
		}
		return dto;
	}
	
	@WebMethod
	public DataObject getWTPartBasicInfoByOuidList(
			@WebParam(name = "ouidList", partName = "ouidList") DataObject ouidList
			) throws Exception{
		DataObject dto = new DataObject();
		
		List<DataObject> inputList =  ouidList.getListValue();
		
		StringBuffer sql = new StringBuffer();
		if (inputList.size() == 1){
			String number = inputList.get(0).getStringValue();
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpart.ida2a2 = '" + number+"' ");
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			
			DataSet oneSet = getDSByPartSql(sql.toString());
			DataSet returnSet = new  DataSet();
			returnSet.setValue(number, oneSet);
			dto.setMapValue(returnSet);
			
		}else{
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpart.ida2a2 " + getInClause(inputList));
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			
			dto = getDTOByPartSql(sql.toString());
		}
		return dto;
	}
	
	@WebMethod
	public DataObject getWTPartDetailInfoByNumberList(
			@WebParam(name = "numberList", partName = "numberList") DataObject numberList , 
			@WebParam(name = "attrList", partName = "attrList") DataObject attrList
			) throws Exception{
		DataObject dto = new DataObject();
		
		List<DataObject> inputList =  numberList.getListValue();
		
		StringBuffer sql = new StringBuffer();
		if (inputList.size() == 1){
			String number = inputList.get(0).getStringValue();
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpartmaster.wtpartnumber = '" + number+"' ");
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			sql.append(" and wtpart.latestiterationinfo = 1");
			sql.append(" order by wtpart.createstampa2 desc");
			
			DataSet oneSet = getDSByPartSql(sql.toString());
			String objOutOuid = (String) oneSet.getValue("objOUID");
			
			List<DataSet> softAttrList = getSoftValueByObj(objOutOuid , attrList);
			for (int i=0 ; i<softAttrList.size() ; i++){
				DataSet exchangeDS = softAttrList.get(i);
				oneSet.setValue((String)exchangeDS.getValue("softname"), exchangeDS.getValue("softvalue"));
			}
			
			DataSet returnSet = new  DataSet();
			returnSet.setValue(number, oneSet);
			dto.setMapValue(returnSet);
			
		}else{
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpartmaster.wtpartnumber " + getInClause(inputList));
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			sql.append(" and wtpart.latestiterationinfo = 1");
			sql.append(" order by wtpart.createstampa2 desc");
			
			dto = getDTOByPartSql(sql.toString());
			
			//构建软属性输入列表
			List<String> inputOuidList = new ArrayList<String>();
			DataSet levelOneSet = dto.getMapValue();
			List<KeyValuePair> values = levelOneSet.getValues();
			for (KeyValuePair p : values) {
				inputOuidList.add(p.getKey());
			}
			List<DataSet> softvalueDS = getSoftValueByList(inputOuidList , attrList);
			
			DataSet outputSet = dto.getMapValue();
			List<KeyValuePair> outputValues = outputSet.getValues();
			for (KeyValuePair p : outputValues) {
				String key = p.getKey();
				DataSet value = (DataSet)p.getValue().getMapValue();
				for (int i=0 ; i<softvalueDS.size() ;i++){
					if (key.equalsIgnoreCase((String) softvalueDS.get(i).getValue("objOUID"))){
						value.setValue((String) softvalueDS.get(i).getValue("softname"), 
							softvalueDS.get(i).getValue("softvalue"));
					}
				}
				
			}	
		}
		return dto;
	}
	
	@WebMethod
	public DataObject getWTPartDetailInfoByOuidList(
			@WebParam(name = "ouidList", partName = "ouidList") DataObject ouidList
			) throws Exception{
		DataObject dto = new DataObject();
		
		List<DataObject> inputList =  ouidList.getListValue();
		
		StringBuffer sql = new StringBuffer();
		if (inputList.size() == 1){
			String number = inputList.get(0).getStringValue();
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpart.ida2a2 = '" + number+"' ");
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			sql.append(" and wtpart.latestiterationinfo = 1");
			sql.append(" order by wtpart.createstampa2 desc");
			
			DataSet oneSet = getDSByPartSql(sql.toString());
			DataSet returnSet = new  DataSet();
			returnSet.setValue(number, oneSet);
			dto.setMapValue(returnSet);
			
		}else{
			sql.append("SELECT wtpart.ida2a2 as objOUID,");
			sql.append("wtpartmaster.wtpartnumber as objnumber,");
			sql.append("wtpartmaster.name as objname,");
			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
			sql.append("wtview.name as objview,");
			sql.append("wtpart.statestate as state,");
			sql.append("wtuser.name as creator,");
			sql.append("wtpart.createstampa2 as createtime,");
			sql.append("wtpart.modifystampa2 as modifytime");
			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
			sql.append(" and wtpart.ida2a2 " + getInClause(inputList));
			sql.append(" and wtpart.ida3view = wtview.ida2a2");
			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
			sql.append(" and wtpart.latestiterationinfo = 1");
			sql.append(" order by wtpart.createstampa2 desc");
			
			dto = getDTOByPartSql(sql.toString());
		}
		return dto;
	}
	
	@WebMethod
	public DataSet getDocDetailInfoByOuid(
			@WebParam(name = "docOuid", partName = "docOuid") String docOuid 

	 		){
		DataSet dto = new DataSet();
		return dto;
	}
	
	@WebMethod
	public DataSet getEPMDetailInfoByOuid(
			@WebParam(name = "epmOuid", partName = "epmOuid") String epmOuid 

	 		){
		DataSet dto = new DataSet();
		return dto;
	}
	
	
	@WebMethod
	public DataObject getDescribeLinkDocByPartOuid(
			@WebParam(name = "partOuid", partName = "partOuid") String partOuid 

	 		){
		DataObject dto = new DataObject();
		return dto;
	}
	
	@WebMethod
	public DataObject getReferenceLinkDocByPartOuid(
			@WebParam(name = "partOuid", partName = "partOuid") String partOuid 

	 		){
		DataObject dto = new DataObject();
		return dto;
	}
	
	@WebMethod
	public DataObject getEPMLinkDocByPartOuid(
			@WebParam(name = "partOuid", partName = "partOuid") String partOuid 

	 		){
		DataObject dto = new DataObject();
		return dto;
	}
	
	@WebMethod
	public DataObject getAlternatesByPartOuid(
			@WebParam(name = "linkOuid", partName = "linkOuid") String linkOuid 

	 		){
		DataObject dto = new DataObject();
		return dto;
	}
	
	
	@WebMethod
	public DataObject getSubstituteByLinkOuid(
			@WebParam(name = "linkOuid", partName = "linkOuid") String linkOuid 

	 		){
		DataObject dto = new DataObject();
		return dto;
	}
	

	@WebMethod
	public DataObject getBomByOuidList(
			@WebParam(name = "partOuidList", partName = "partOuidList") DataObject partOuidList 
			,@WebParam(name = "linkAttrList", partName = "linkAttrList") DataObject linkAttrList
	 		){
		DataObject dto = new DataObject();
		return dto;
	}
	

	@WebMethod
	public int getUsedPartCount(
			@WebParam(name = "partOuid", partName = "partOuid") String partOuid 
	 		){
		int count = 0;
		return count;
	}
	
	
	
	private String getInClause(List<DataObject> dtoList){
		String returnString = "";
		for (int i=0 ; i<dtoList.size() ; i++){
			returnString += "'" + dtoList.get(i).getStringValue() + "',";
		}
		returnString = returnString.substring(0 , returnString.length()-1);
		returnString = "in (" + returnString + ")";
		
		return returnString;
	}
	
	private DataSet getDSByPartSql(String sql) throws Exception{
		DataSet returnSet = new DataSet();
		
		SQLResult sql_QUERY = SQLUtil.SQL_QUERY(connectString, sql);
		List<SQLRow> dataList = sql_QUERY.getData();

		if (dataList.size()>0){	
			SQLRow row = dataList.get(0);		//取日期最后的一条记录，为最终版
			String[] rowColumns = row.getColumns();
			Object[] rowValues = row.getData();
			
			for (int i=0 ; i<rowColumns.length ; i++){
				String key = rowColumns[i];
				Object value = rowValues[i];
				if (key.equalsIgnoreCase("objOUID")){
					returnSet.setValue(key, value.toString());
				}else{
					returnSet.setValue(key, value);
				}
			}
		}

		return returnSet;
	}
	
	private DataObject getDTOByPartSql(String sql) throws Exception{
		DataObject returnDTO = new DataObject();
		List<String> exchangeList = new ArrayList<String>();
		
		DataSet returnDS = new DataSet();
		SQLResult sql_QUERY = SQLUtil.SQL_QUERY(connectString, sql);
		List<SQLRow> dataList = sql_QUERY.getData();
		Iterator<?> iterator = dataList.iterator();
		while (iterator.hasNext()) {
			SQLRow row = (SQLRow) iterator.next();
			
			DataSet oneRowDS = new DataSet();
			boolean insertFlag = false;
			String objNumber = row.getText("objnumber");
			if (!exchangeList.contains(objNumber)){
				String[] rowColumns = row.getColumns();
				Object[] rowValues = row.getData();
				
				for (int i=0 ; i<rowColumns.length ; i++){
					String key = rowColumns[i];
					Object value = rowValues[i];
					if (key.equalsIgnoreCase("objOUID")){
						oneRowDS.setValue(key, value.toString());
					}else{
						oneRowDS.setValue(key, value);
					}
				}
				insertFlag = true;
			}
			
			if (insertFlag){
				returnDS.setValue(objNumber, oneRowDS);
				exchangeList.add(objNumber);
				insertFlag = false;
			}
		}
		
		returnDTO.setMapValue(returnDS);
		
		return returnDTO;
	}
	
	
//	@WebMethod
//	public DataObject getWTPartBasicInfoByOuidList(List<String> ouidList){
//		DataObject dto = new DataObject();
//		
//		return dto;
//	}
//	
//	@WebMethod
//	public DataSet getWTPartDetailInfoByNumber(){
//		
//	}
//	
//	@WebMethod
//	public DataSet getWTPartDetailInfoByOuidList(){
//		
//	}
//	
//	@WebMethod
//	public DataSet getWTPartDetailInfoByOuidList(){
//		
//	}
	
	
	
//	/*
//	 * 获取windchill对象详细信息,含属性列表中的属性
//	 *
//	 * 传入参数 :
//	 * @param partNumber:零部件号
//	 * @param infoType:
//	 * 		"WTPart",零部件信息;
//	 * 		"WTDocument":文档;
//	 * 		"EPMDocument":参考文档;
//	 */
//	@WebMethod
//	public DataSet getObjDetailInfo(
//			@WebParam(name = "objNumber", partName = "objectNumber") String objNumber ,
//	 		@WebParam(name = "infoType", partName = "infoType") String infoType ,
//	 		@WebParam(name = "attributeSet", partName = "attributeSet") DataSet attributeSet
//	 		) throws Exception{
//		DataSet returnSet = new DataSet();
//		
//		StringBuffer sql = new StringBuffer();
//		//拼接sql语句
//		if (infoType.equalsIgnoreCase("WTPart")){
//			sql.append("SELECT wtpart.ida2a2 as objOUID,");
//			sql.append("wtpartmaster.wtpartnumber as objnumber,");
//			sql.append("wtpartmaster.name as objname,");
//			sql.append("(wtpart.versionida2versioninfo||'.'||wtpart.iterationida2iterationinfo) as version,");
//			sql.append("wtview.name as objview,");
//			sql.append("wtpart.statestate as state,");
//			sql.append("wtuser.name as creator,");
//			sql.append("wtpart.createstampa2 as createtime,");
//			sql.append("wtpart.modifystampa2 as modifytime");
//			sql.append(" from wtpartmaster,wtpart,wtuser,wtview");
//			sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
//			sql.append(" and wtpart.ida3view = wtview.ida2a2");
//			sql.append(" and wtpartmaster.wtpartnumber = '" + objNumber+"' ");
//			sql.append(" and wtpart.ida3b2iterationinfo = wtuser.ida2a2");
//			sql.append(" and wtpart.latestiterationinfo = 1");
//			sql.append(" order by wtpart.createstampa2 desc");
//				
//		}else if (infoType.equalsIgnoreCase("WTDocument")){
//			sql.append("SELECT wtdocument.ida2a2 as objOUID,");
//			sql.append("wtdocumentmaster.wtdocumentnumber as objnumber,");
//			sql.append("wtdocumentmaster.name as objname,");
//			sql.append("(wtdocument.versionida2versioninfo||'.'||wtdocument.iterationida2iterationinfo) as version,");
//			sql.append("wtdocument.statestate as state,");
//			sql.append("wtuser.name as creator,");
//			sql.append("wtdocument.createstampa2 as createtime,");
//			sql.append("wtdocument.modifystampa2 as modifytime");
//			sql.append(" from wtdocumentmaster,wtdocument,wtuser");
//			sql.append(" where wtdocumentmaster.ida2a2 = wtdocument.ida3masterreference");
//			sql.append(" and wtdocumentmaster.wtdocumentnumber = '" + objNumber+"' ");
//			sql.append(" and wtdocument.ida3b2iterationinfo = wtuser.ida2a2");
//			sql.append(" and wtdocument.latestiterationinfo = 1");
//			sql.append(" order by wtdocument.createstampa2 desc");
//			
//		}else if (infoType.equalsIgnoreCase("EPMDocument")){
//			sql.append("SELECT epmdocument.ida2a2 as objOUID,");
//			sql.append("epmdocumentmaster.documentnumber as objnumber,");
//			sql.append("epmdocumentmaster.name as objname,");
//			sql.append("(epmdocument.versionida2versioninfo||'.'||epmdocument.iterationida2iterationinfo) as version,");
//			sql.append("epmdocument.statestate as state,");
//			sql.append("wtuser.name as creator,");
//			sql.append("epmdocument.createstampa2 as createtime,");
//			sql.append("epmdocument.modifystampa2 as modifytime");
//			sql.append(" from epmdocumentmaster,epmdocument,wtuser");
//			sql.append(" where epmdocumentmaster.ida2a2 = epmdocument.ida3masterreference");
//			sql.append(" and epmdocumentmaster.documentnumber = '" + objNumber+"' ");
//			sql.append(" and epmdocument.ida3b2iterationinfo = wtuser.ida2a2");
//			sql.append(" and epmdocument.latestiterationinfo = 1");
//			sql.append(" order by epmdocument.createstampa2 desc");
//		}
//		
//		//处理基本信息
//		String objID = "";
//		
//		SQLResult sql_QUERY = SQLUtil.SQL_QUERY("tegwindchill", sql.toString());
//		List<SQLRow> data = sql_QUERY.getData();
//
//		if (data.size()>0){	
//			SQLRow rowData = data.get(0);	//取日期最后的一条记录，为最后的版本
//			objID = (String) rowData.getText("objOUID");
//			String[] rowColumns = rowData.getColumns();
//			Object[] rowValues = rowData.getData();
//			for (int i=0 ; i<rowColumns.length ; i++){
//				returnSet.setValue(rowColumns[i], rowValues[i]);
//			}
//		}
//		
//		//处理软属性
//		
//		DataSet softAttrSet = getSoftValue( infoType , objID , attributeSet );
//		List<KeyValuePair> values = softAttrSet.getValues();
//		for (KeyValuePair p : values) {
//			returnSet.setValue(p.getKey(), p.getValue().getStringValue());
//		}
//
//		return returnSet;
//	}
	

	
//	/*
//	 * 获取零部件关联对象列表
//	 *
//	 * 传入参数 :
//	 * @param partNumber:零部件号
//	 * @param infoType:
//	 * 		"WTPartDescribeLink":说明文档;
//	 * 		"WTPartReferenceLink":参考文档;
//	 * 		"EPMLink":说明EPM
//	 * 		"AlternatesPart":全局替代件;
//	 */
//	@WebMethod
//	public DataObject getPartRelatdObjList(
//			@WebParam(name = "partNumber", partName = "partNumber") String partNumber ,
//	 		@WebParam(name = "infoType", partName = "infoType") String infoType , 
//	 		@WebParam(name = "attributeSet", partName = "attributeSet") DataSet attributeSet) throws Exception{
//		DataObject dto = new DataObject();
//		List<DataObject> dtoList = new ArrayList<DataObject>();
//		
//		//获取wtpart的OUID
//		String wtpartOUID = "";
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT wtpart.ida2a2 as objID");
//		sql.append(" from wtpartmaster, wtpart");
//		sql.append(" where wtpartmaster.ida2a2 = wtpart.ida3masterreference");
//		sql.append(" and wtpartmaster.wtpartnumber = '" + partNumber + "'");
//		sql.append(" and wtpart.latestiterationinfo = 1");
//		sql.append(" order by wtpart.createstampa2 desc");
//		
//		SQLResult sql_QUERY = SQLUtil.SQL_QUERY("tegwindchill", sql.toString());
//		List<SQLRow> data = sql_QUERY.getData();
//		if (data.size()>0){	
//			SQLRow rowData = data.get(0);
//			wtpartOUID = (String) rowData.getText("objID");
//		}
//		
//		if (infoType.equalsIgnoreCase("WTPartDescribeLink")) {
//			StringBuffer sqlLink = new StringBuffer();
//			sqlLink.append("SELECT WTDocumentMaster.wtdocumentnumber as docNumber");
//			sqlLink.append(" from WTPartDescribeLink,WTDocumentMaster,WTDocument ");
//			sqlLink.append(" where WTPartDescribeLink.ida3b5 = WTDocument.ida2a2");
//			sqlLink.append(" and wtdocumentmaster.ida2a2 = wtdocument.ida3masterreference");
//			sqlLink.append(" and WTPartDescribeLink.ida3a5 = '" + wtpartOUID + "'");
//			
//			SQLResult sqlLink_QUERY = SQLUtil.SQL_QUERY("tegwindchill", sqlLink.toString());
//			List<SQLRow> dataLink = sqlLink_QUERY.getData();
//			Iterator<?> iterator = dataLink.iterator();
//			while (iterator.hasNext()) {
//				DataSet ds = new DataSet();
//				SQLRow row = (SQLRow) iterator.next();
//				String docNumber = (String)row.getText("docNumber");
//				ds = getObjDetailInfo(docNumber , "WTDocument" , attributeSet);
//				DataObject exchangeDTO = new DataObject();
//				exchangeDTO.setMapValue(ds);
//				dtoList.add(exchangeDTO);
//			}
//
//		}else if (infoType.equalsIgnoreCase("WTPartReferenceLink")) {			
//			StringBuffer sqlLink = new StringBuffer();
//			sqlLink.append("SELECT WTDocumentMaster.wtdocumentnumber as docNumber");
//			sqlLink.append(" from WTPartReferenceLink,WTDocumentMaster ");
//			sqlLink.append(" where WTPartReferenceLink.ida3b5 = WTDocumentMaster.ida2a2");
//			sqlLink.append(" and WTPartReferenceLink.ida3a5 = '" + wtpartOUID + "'");
//			
//			SQLResult sqlLink_QUERY = SQLUtil.SQL_QUERY("tegwindchill", sqlLink.toString());
//			List<SQLRow> dataLink = sqlLink_QUERY.getData();
//			Iterator<?> iterator = dataLink.iterator();
//			while (iterator.hasNext()) {
//				DataSet ds = new DataSet();
//				SQLRow row = (SQLRow) iterator.next();
//				String docNumber = (String)row.getText("docNumber");
//				ds = getObjDetailInfo(docNumber , "WTDocument" , attributeSet);
//				DataObject exchangeDTO = new DataObject();
//				exchangeDTO.setMapValue(ds);
//				dtoList.add(exchangeDTO);
//			}
//		}else if (infoType.equalsIgnoreCase("EPMLink")) {
//			
//			StringBuffer sqlLink = new StringBuffer();
//			sqlLink.append("SELECT EPMDocumentMaster.documentnumber as docNumber");
//			sqlLink.append(" from EPMBuildhistory,EPMDocumentMaster,EPMDocument ");
//			sqlLink.append(" where EPMBuildhistory.ida3b5 = '" + wtpartOUID + "'");
//			sqlLink.append(" and EPMBuildhistory.ida3a5 = EPMDocument.ida2a2");
//			sqlLink.append(" and EPMDocumentMaster.ida2a2 = EPMDocument.ida3masterreference");
//
//			SQLResult sqlLink_QUERY = SQLUtil.SQL_QUERY("tegwindchill", sqlLink.toString());
//			List<SQLRow> dataLink = sqlLink_QUERY.getData();
//			Iterator<?> iterator = dataLink.iterator();
//			while (iterator.hasNext()) {
//				DataSet ds = new DataSet();
//				SQLRow row = (SQLRow) iterator.next();
//				String docNumber = (String)row.getText("docNumber");
//				ds = getObjDetailInfo(docNumber , "EPMDocument" , attributeSet);
//				DataObject exchangeDTO = new DataObject();
//				exchangeDTO.setMapValue(ds);
//				dtoList.add(exchangeDTO);
//			}
//			
//			//通过三维找二维，表：EPMReferenceLink，通过master找
//		}else if (infoType.equalsIgnoreCase("AlternatesPart")) {
//			StringBuffer sqlLink = new StringBuffer();
//			sqlLink.append("SELECT WTPartAlternateLink.replacementtype as replacementtype,");
//			sqlLink.append("WTPartAlternateLink.ida3b5 as childMasterID,");
//			sqlLink.append(" from WTPartAlternateLink,wtpartmaster");
//			sqlLink.append(" where WTPartAlternateLink.ida5a5 = wtpartmaster.ida2a2");
//			sqlLink.append(" and wtpartmaster.wtpartnumber = '" + partNumber+"' ");
//
//			SQLResult sqlLink_QUERY = SQLUtil.SQL_QUERY("tegwindchill", sqlLink.toString());
//			List<SQLRow> dataLink = sqlLink_QUERY.getData();
//			Iterator<?> iterator = dataLink.iterator();
//			while (iterator.hasNext()) {
//				DataSet ds = new DataSet();
//				SQLRow row = (SQLRow) iterator.next();
//				String docNumber = (String)row.getText("docNumber");
//				ds = getObjDetailInfo(docNumber , "WTDocument" , attributeSet);
//				DataObject exchangeDTO = new DataObject();
//				exchangeDTO.setMapValue(ds);
//				dtoList.add(exchangeDTO);
//			}
//			
//			
////			objectInfoList = getAlternatesByPart(part);
//		}
//		
//		dto.setListValue(dtoList);
//		return dto;
//	}
	

	
//	/*
//	 * 获取BOM特定替代件
//	 *
//	 * 传入参数 :
//	 * fatherPartNumber:父零部件号
//	 * fatherPartNumber:子零部件号
//	 */
//	@WebMethod
//	public DataObject getSubstituteListByPart(@WebParam(name = "fatherPartNumber", partName = "fatherPartNumber") String fatherPartNumber ,
//	 		@WebParam(name = "childPartNumber", partName = "childPartNumber") String childPartNumber 
//	 		){
//		
//		DataObject dto = new DataObject();
//		List<DataObject> listValue = dto.getListValue();
//		return dto;
////		List<DataSet> objectInfoList = new ArrayList<DataSet>();
////		
////		return objectInfoList;
//	}

//	/*
//	 * 获取windchill对象BOM详细信息
//	 *
//	 * 传入参数 :
//	 * @param partNumber:零部件号
//	 * @param infoType:
//	 * 		"WTPart",零部件信息;
//	 * 		"DescribedWTDocument":参考文档;
//	 * 		"ReferenceWTDocument":参考文档;
//	 * 		"EPMDocument":EPM对象;
//	 * 		"AlternatesPart":全局替代件;
//	 */
//	@WebMethod
//	public DataObject getOneLevelBOM(
//			@WebParam(name = "partNumberList", partName = "partNumberList") DataSet partNumberList 
////			,@WebParam(name = "attributeList", partName = "attributeList") DataSet attributeList
//	 		){
//		DataObject dto = new DataObject();
//		return dto;
////		List<DataSet> objectInfoList = new ArrayList<DataSet>();
////		
////		return objectInfoList;
//	}
	
	private List<DataSet> getSoftValueByObj(String objOuid , DataObject attrList) throws Exception{
		List<DataSet> dsList = new ArrayList<DataSet>();

		List<DataObject> exchangeAttrList = attrList.getListValue();
		String attrInClause = getInClause(exchangeAttrList);
		
		StringBuffer sql = new StringBuffer();

		sql.append("select stringvalue.ida3a4 as objOUID,"
				+ "stringdefinition.name as softname,"
				+ "stringdefinition.displayname as displayname"
				+ "stringvalue.value2 as softvalue");
		sql.append(" from stringdefinition,stringvalue");
		sql.append(" where stringvalue.ida3a6 = stringdefinition.ida2a2");
		sql.append(" and stringvalue.ida3a4 = '" +objOuid + "'");
		sql.append(" and stringdefinition.name in " + attrInClause);

		SQLResult sql_QUERY = SQLUtil.SQL_QUERY(connectString, sql.toString());
		List<SQLRow> data = sql_QUERY.getData();
		Iterator<?> iterator = data.iterator();
		while (iterator.hasNext()) {
			SQLRow row = (SQLRow) iterator.next();
			String[] rowColumns = row.getColumns();
			Object[] rowValues = row.getData();
			
			DataSet ds = new DataSet();
			for (int i=0 ; i<rowColumns.length ; i++){
				String key = rowColumns[i];
				Object value = rowValues[i];
				if (key.equalsIgnoreCase("objOUID")){
					ds.setValue(key, value.toString());
				}else{
					ds.setValue(key, value);
				}
			}
			dsList.add(ds);
		}

			
		return dsList;
	}
	
	private List<DataSet> getSoftValueByList(List<String> objOuidList , DataObject attrList) throws Exception{
		List<DataSet> dsList = new ArrayList<DataSet>();

		String ouidInClause = "";
		for (int i=0 ; i<objOuidList.size() ; i++){
			ouidInClause += "'" + objOuidList.get(i) + "',";
		}
		ouidInClause = ouidInClause.substring(0 , ouidInClause.length()-1);
		ouidInClause = "in (" + ouidInClause + ")";
		
		List<DataObject> exchangeAttrList = attrList.getListValue();
		String attrInClause = getInClause(exchangeAttrList);
		
		StringBuffer sql = new StringBuffer();

		sql.append("select stringvalue.ida3a4 as objOUID,"
				+ "stringdefinition.name as softname,"
				+ "stringdefinition.displayname as displayname"
				+ "stringvalue.value2 as softvalue");
		sql.append(" from stringdefinition,stringvalue");
		sql.append(" where stringvalue.ida3a6 = stringdefinition.ida2a2");
		sql.append(" and stringvalue.ida3a4 in " + ouidInClause);
		sql.append(" and stringdefinition.name in " + attrInClause);

		SQLResult sql_QUERY = SQLUtil.SQL_QUERY(connectString, sql.toString());
		List<SQLRow> data = sql_QUERY.getData();
		Iterator<?> iterator = data.iterator();
		while (iterator.hasNext()) {
			SQLRow row = (SQLRow) iterator.next();
			String[] rowColumns = row.getColumns();
			Object[] rowValues = row.getData();
			
			DataSet ds = new DataSet();
			for (int i=0 ; i<rowColumns.length ; i++){
				String key = rowColumns[i];
				Object value = rowValues[i];
				if (key.equalsIgnoreCase("objOUID")){
					ds.setValue(key, value.toString());
				}else{
					ds.setValue(key, value);
				}
			}
			dsList.add(ds);
		}
	
		return dsList;
	}
	
}
