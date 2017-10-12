$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'analyzetanzhen/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '经销商名', name: 'dealerName', index: 'dealer_name', width: 50 }, 			
			{ label: '公司名', name: 'companyName', index: 'company_name', width: 50 }, 			
			{ label: '4s店', name: 's4Name', index: 's4_name', width: 50 }, 			
			{ label: '手机号', name: 'mobile', index: 'mobile', width: 50 }, 			
			{ label: '进入时间', name: 'incomingTime', index: 'incoming_time', width: 50 }, 			
			{ label: '进入市场', name: 'keepTime', index: 'keep_time', width: 50 }, 			
			{ label: '状态  0：低   1：高', name: 'status', index: 'status', width: 50 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 50 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        //shrinkToFit: false,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        //$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		q:{startTime: null,
		   endTime: null
		},
		d:{comName: []},
		analyzeTanzhen:{}
	},
	methods: {
		
		dateDefind () {
	        var d, s;
	        d = new Date();
	        s = d.getFullYear() + "-";             //取年份
	        s = s + (d.getMonth() -2) + "-";//取月份
	        s += d.getDate() + " ";         //取日期
	        s += d.getHours() + ":";       //取小时
	        s += d.getMinutes() + ":";    //取分
	        self.startTime = s;
	        //初始化
	        $('#startTime').datetimepicker({
	          startDate: s,
	          minView: "hour", //选择日期后，不会再跳转去选择时分秒
	          language: 'zh-CN',
	          format: 'yyyy-mm-dd hh:ii',
	          todayBtn: 1,
	          forceParse: true,
	          endDate: new Date(),
	          minuteStep: 30,
	          autoclose: 1
	        });
	        //当选择器隐藏时，讲选择框只赋值给data里面的time
	        $('#startTime').datetimepicker()
	          .on('hide', function (ev) {
	            var value = $("#startTime").val();
	            vm.q.startTime = value;
	          });
	        $('#endTime').datetimepicker({
		          startDate: s,
		          minView: "hour", //选择日期后，不会再跳转去选择时分秒
		          language: 'zh-CN',
		          format: 'yyyy-mm-dd hh:ii',
		          todayBtn: 1,
		          endDate: new Date(),
		          minuteStep: 30,
		          forceParse: true,
		          autoclose: 1
		        });
	        $('#endTime').datetimepicker()
	          .on('hide', function (ev) {
	            var value = $("#endTime").val();
	            vm.q.endTime = value;
	          });
	      },
	   
		query: function () {
			vm.reload();
		},
		
		queryByCon: function(){
			var comName1 = JSON.stringify(vm.d.comName);
			var url= baseURL + "/analyzetanzhen/query";
			$.ajax({
			type: "POST",
			url: url,
			//contentType: "application/json",
			data: {startTime: vm.q.startTime,
				   comName: comName1},
			 success: function(result){
			   if(result.code === 0){
				   var option = myChart.getOption();
				  //给图标标题赋值
			      option.legend.data = result.bKey;
			      //读取横坐标值
			      option.xAxis[0].data = result.xKey;   
			      //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
			      for(var i = 0; i < result.series.length; i++) {
			          option.series[i]={
			          name : result.bKey[i],
			        	  type : "bar",
			        	  data : result.series[i]
			        };
			     }
	            //过渡控制，隐藏loading（读取中）
	            //myChart.hideLoading();
	            // 为echarts对象加载数据
	            myChart.setOption(option,true);
				vm.reload();
		       }else{
			        alert(result.msg);
			   }
			 }
			});
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.analyzeTanzhen = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.analyzeTanzhen.id == null ? "analyzetanzhen/save" : "analyzetanzhen/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.analyzeTanzhen),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "analyzetanzhen/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "analyzetanzhen/info/"+id, function(r){
                vm.analyzeTanzhen = r.analyzeTanzhen;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
			    postData:{'comName': JSON.stringify(vm.d.comName),
			    	          'startTime': vm.q.startTime},
                page:page
            }).trigger("reloadGrid");
		}
	},
	mounted: function () {
	      this.dateDefind();
	}
});
var myChart;
var myChart1;
$(function () {
//初始化mutiple select
$('#fourSName').multiselect(
	{
		selectedClass: 'multiselect-selected',//选中项样式
		enableClickableOptGroups: true,//同时取组或者all  
        enableCollapsibleOptGroups: true,//组可折叠  
        enableFiltering: true,//过滤  
        filterPlaceholder: '4s店...',
        includeSelectAllOption: true,
        selectAllText: '选择所有店面',
        buttonWidth: '230px',
        buttonClass: 'btn btn-sy',
        nonSelectedText: '选择店面',
        onDropdownHide: function(event) {
        	    vm.d.comName = [];
	    	    $("#fourSName option:selected").each(function () {  
	    	    		vm.d.comName.push($(this).val());  
	    	    });  
        }
	});

//加载部门树
$.get(baseURL + "sys/dept/select/"+"tanzhen", function(r){
	var optgroups = [{
		label : [],
		children : [{
			label: [],
			value: []
		}]
	}];
	var children = [{
		label: "",
		value: ""
	}];
	for(var i = 0; i < r.deptList.length; i++) {
		children = [{
			label: "",
			value: ""
		}];
		for(var j = 0; j < r.deptList[i].list.length; j++) {
			children[j] ={
					label : r.deptList[i].list[j].name,
					value : r.deptList[i].list[j].deptId
			}
		}
		optgroups[i] = {
				label  : r.deptList[i].parentName,
				children: children
		}
	}
	$('#fourSName').multiselect('dataprovider', optgroups);
});
//var optgroups = [
//    {
//        label: 'Group 6', children: [
//            {label: 'Option 1.1', value: '15-1'},
//            {label: 'Option 1.2', value: '5-2'},
//            {label: 'Option 1.3', value: '1-3'}
//        ]
//    },
//    {
//        label: 'Group 7', children: [
//            {label: 'Option 2.1', value: '15'},
//            {label: 'Option 2.2', value: '25'},
//            {label: 'Option 2.3', value: '35', disabled: true}
//        ]
//    }
//];

////初始化ECHARTS
//require.config({
//    paths: {
//        echarts: '../../plugins/echarts'
//    }
//});
//require(
//        [
//            'echarts',
//            'echarts/chart/bar',
//            'echarts/chart/line'
//
//        ],
  
$(function() {
    //--- BAR ---
	myChart = echarts.init(document.getElementById('echarts_bar'),'macarons'); 
    // 显示标题，图例和空的坐标轴
    var option = {  
        title: {  
            text: '统计'  
        },  
        background: '#fff',
        width: '100%',
        tooltip: {trigger: 'axis'},  
        toolbox: {
            show: true,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        legend: {  
            data:[]  
        },  
        xAxis: { 
        	    type: 'category',
        	    silent : false,
            data: []  
        },  
        yAxis: {
        	type: 'value',
            splitArea: {
                show: true
            }
        },  
        series: []  
    };  
    $.ajax({
        type: "POST",
        url: baseURL + "analyzetanzhen/query",
        contentType: "application/json",
        dataType : "json",
        data: {},
        success: function (result) {
        	 //给图标标题赋值
         option.legend.data = result.bKey;
         //读取横坐标值
         option.xAxis.data = result.xKey;

          //驱动图表生成的数据内容，数组中每一项代表一个系列的特殊选项及数据
         for(var i = 0; i < result.series.length; i++) {
        	    option.series[i]={
        	    	name : result.bKey[i],
        	    	type : "bar",
        	    	data : result.series[i]
        	    };
         }
            //过渡控制，隐藏loading（读取中）
            //myChart.hideLoading();
             // 为echarts对象加载数据
            myChart.setOption(option,true);
        }
    });
    //加载第二个
    myChart1 = echarts.init(document.getElementById('echarts_bar1'),'macarons'); 
    var option1 = {
    	    tooltip: {
    	        trigger: 'axis',
    	        formatter: function(params, ticket, callback) {

    	            var res = params[0].name;

    	            for (var i = 0, l = params.length; i < l; i++) {
    	                if (params[i].seriesType === 'line') {
    	                    res += '<br/>' + params[i].seriesName + ' : ' + (params[i].value ? params[i].value : '-') + 'h';
    	                } else {
    	                    res += '<br/>' + params[i].seriesName + ' : ' + (params[i].value ? params[i].value : '-') + '个';
    	                }
    	            }
    	            return res;

    	        }
    	    },
    	    grid: {
    	        containLabel: true
    	    },
    	    legend: {
    	        data: ['时间', '人均个数', '总体个数']
    	    },
    	    xAxis: [{
    	        type: 'category',
    	        axisTick: {
    	            alignWithLabel: true
    	        },
    	        data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
    	    }],
    	    dataZoom: [{
    	        type: 'slider',
    	        xAxisIndex: 0,
    	        filterMode: 'empty',
    	        start: 0,
    	        end: 100
    	    }, {
    	        type: 'slider',
    	        yAxisIndex: 0,
    	        filterMode: 'empty',
    	        start: 0,
    	        end: 100
    	    }, {
    	        type: 'inside',
    	        xAxisIndex: 0,
    	        filterMode: 'empty',
    	        start: 0,
    	        end: 100
    	    }, {
    	        type: 'inside',
    	        yAxisIndex: 0,
    	        filterMode: 'empty',
    	        start: 0,
    	        end: 100
    	    }],
    	    yAxis: [{
    	        type: 'value',
    	        name: '时间',
    	        min: 0,
    	        position: 'left',
    	        axisLabel: {
    	            formatter: '{value} h'
    	        }
    	    }, {
    	        type: 'value',
    	        name: '个数',
    	        min: 0,
    	        position: 'right',
    	        axisLabel: {
    	            formatter: '{value} 个'
    	        }
    	    }],
    	    series: [{
    	        name: '时间',
    	        type: 'line',
    	        label: {
    	            normal: {
    	                show: true,
    	                position: 'top',
    	            }
    	        },
    	        lineStyle: {
    	            normal: {
    	                width: 3,
    	                shadowColor: 'rgba(0,0,0,0.4)',
    	                shadowBlur: 10,
    	                shadowOffsetY: 10
    	            }
    	        },
    	        data: [1, 13, 37, 35, 15, 13, 25, 21, 6, 45, 32, 2]
    	    }, {
    	        name: '人均个数',
    	        type: 'bar',
    	        yAxisIndex: 1,
    	        label: {
    	            normal: {
    	                show: true,
    	                position: 'top'
    	            }
    	        },
    	        data: [22, 22, 23, 77, 24, 55, 55, 89, 98, 164, 106, 224]
    	    }, {
    	        name: '总体个数',
    	        type: 'bar',
    	        yAxisIndex: 1,
    	        label: {
    	            normal: {
    	                show: true,
    	                position: 'top'
    	            }
    	        },
    	        data: [201, 222, 223, 777, 244, 255, 555, 879, 938, 1364, 1806, 2324]
    	    }]
    	};
    myChart1.setOption(option1,true);
  }
)
//)  
});  