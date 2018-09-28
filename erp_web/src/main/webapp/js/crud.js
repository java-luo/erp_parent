$(function(){
		$('#dg').datagrid({    
		    url:name+'_getList',    
		    columns:columns  ,
		    pagination:true
		});  
		$('#dg').datagrid({
			toolbar: [{
				iconCls: 'icon-edit',
				text:'增加',
				handler: function(){
					method='save';
					$("#dd").window('open');
					$("#editForm").form('clear');
				}
			}]
		});
		$("#btnSearch").click(function(){
			//将表单中得数据转换为JSON对象
			var formdata=$('#searchFrom').serializeJSON();
			//将Json对象转换为字符串
			alert(JSON.stringify(formdata));
			//$('属性选择器').插件名(方法名,参数);
			
			//重新带着参数加载数据
			$("#dg").datagrid('load',formdata);
			 /* $.ajax({
				url:'dep_getList',
				
				data:formdata,
				datatype:'json',
				type:'post',
				success:function(rtn){
				$('#dg').datagrid('loadData',rtn);
				}
			})  */

		});
		$("#btnSave").click(function(){
			//将表单中得数据转换为JSON对象
			var formdata=$('#editForm').serializeJSON();
			//将Json对象转换为字符串
			//$('属性选择器').插件名(方法名,参数);
			 $.ajax({
				url:name+'_'+method,
				data:formdata,
				datatype:'json',
				type:'post',
				success:function(value){
					var v=JSON.parse(value)
					if(v.success){
						$("#dd").window('close');
						$('#dg').datagrid('reload');
					}
					$.messager.alert('提示',v.msg)
				}
			})  
		});
		$('#dd').dialog({    
		    title: '编辑',    
		    width: 400,    
		    height: 200,    
		    closed: true,    
		    modal: true   
		});  
	})
	function del(uuid){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	$.ajax({
		    		url:name+'_delete?'+'id='+uuid,
		    		datatype:'json',
		    		type:'post',
		    		success:function(value){
		    		var v=JSON.parse(value)
		    		if(v.success){
						$('#dg').datagrid('reload');
					}
					$.messager.alert('提示',v.msg)
		    		}
		    	})
		    }    
		});
	}
	function update(uuid){
		$('#dd').window('open');
		$('#editForm').form('clear');
		method = "update";
		$('#editForm').form('load',name+'_get?id=' + uuid);
	}