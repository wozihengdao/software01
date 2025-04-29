// order-stats.js
export function initOrderStats() {
    // 初始化折线图
    const initLineChart = () => {
        const echarts = window.echarts;
        const dom = document.getElementById('container01');
        if (!dom) return;

        const myChart = echarts.init(dom, null, {
            renderer: 'canvas',
            useDirtyRect: false
        });

        const option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            title: {
                text: '工单统计',
                left: 'center'
            },
            legend: {
                data: ['保养单', '备件单','巡检单', '检测单','维修单'],
                top: 30
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                boundaryGap: false
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '维修单',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    label: {
                        show: true,
                        position: 'top'
                    },
                    data: [10, 14, 12, 10, 7, 12, 13]
                },
                {
                    name: '保养单',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    label: {
                        show: true,
                        position: 'top'
                    },
                    data: [9, 12, 14, 16, 16, 14, 12]
                },
                {
                    name: '备件单',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    label: {
                        show: true,
                        position: 'top'
                    },
                    data: [12, 12, 13, 14, 18, 12, 9]
                },
                {
                    name: '巡检单',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    label: {
                        show: true,
                        position: 'top'
                    },
                    data: [12, 17, 11, 13, 17, 13, 12]
                },
                {
                    name: '检测单',
                    type: 'line',
                    stack: 'Total',
                    areaStyle: {},
                    label: {
                        show: true,
                        position: 'top'
                    },
                    data: [12, 12, 17, 14, 18, 20, 16]
                }
            ]
        };

        myChart.setOption(option);
        window.addEventListener('resize', myChart.resize);
    };

    // 初始化饼图
    const initPieChart = () => {
        const echarts = window.echarts;
        const dom02 = document.getElementById('container02');
        if (!dom02) return;

        const myChart02 = echarts.init(dom02, null, {
            renderer: 'canvas',
            useDirtyRect: false
        });

        const option02 = {
            title: {
                text: '占比分析',
                subtext: '',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                top: '85%',
                left: 'center'
            },
            series: [
                {
                    center: ['50%', '45%'],
                    name: '详细信息',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    padAngle: 5,
                    itemStyle: {
                        borderRadius: 10
                    },
                    label: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: 40,
                            fontWeight: 'bold'
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    data: [
                        { value: 30, name: '超时完成' },
                        { value: 120, name: '按时完成' },
                        { value: 50, name: '待完成' },
                        { value: 30, name: '超时未完成' },
                    ]
                }
            ]
        };

        myChart02.setOption(option02);
        window.addEventListener('resize', myChart02.resize);
    };

    // 初始化数据请求
    const fetchData = () => {
        axios.post("http://localhost:7469/MaintenanceManage/repairOrder/allOrder", storage.get('user').username)
            .then((res) => {
                // 这里可以添加数据处理逻辑
                console.log('完整工单数据:', res.data);
            })
            .catch(error => {
                console.error('数据请求失败:', error);
            });
    };

    // 执行初始化
    initLineChart();
    initPieChart();
    fetchData();
}