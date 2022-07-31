let vm = new Vue({
    el: '.main-content',
    data: {
        pageInfo: {
            current: 1,
            size: 5
        },//接受后台页对象
        type: '',
        statute: ''
    },
    methods: {
        select: function (pageNum, pageSize) {
            //分页查询返回信息放入pageInfo
            //分页查询返回信息放入pageInfo
            axios({
                url: `/manager/statute/select/${pageNum}/${pageSize}`,
                params: {type: this.type}
            }).then(response => {
                // console.log(response);
                this.pageInfo = response.data.data;
            }).catch(error => {
                layer.msg(error.message);
            })
        },
        toUpdate: function (status_id) {
            axios({
                url: '/manager/statute/selectOne',
                params: {id: status_id}
            }).then(response => {
                if (response.data.code == 500) {
                    layer.msg(response.data.msg)
                    return;
                }
                // console.log(response.data);
                //返回数据，绑定到当前父窗口layer的obj上，传递给子窗口
                //对一些实时性要求不高的数据更新，可以不查询数据，直接方法传参对象，传递到layer子窗口
                layer.obj = response.data.data;
                // console.log(layer);
                let index = layer.open({
                    type: 2,
                    title: '更新statute',
                    content: '/manager/statute/statute-update.html',
                    area: ['60%', '80%'],
                    end: () => {//将上下文中的this传递到end的回调函数中
                        //刷新页面数据
                        this.select(this.pageInfo.current, this.pageInfo.size);
                    }
                });
            })
        },
        doDelete: function (sid) {
            alert(sid);
        }
    },
    created: function () {
        this.select(1, this.pageInfo.size);
    }
});