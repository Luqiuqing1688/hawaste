new Vue({
    el: '#main-container',
    data: {
        appVersion: {}
    },
    methods: {
        doUpdate: function () {
            axios({
                url: '/manager/app/saveOrUpdate',
                method: 'post',
                data: this.appVersion
            }).then(response => {
                parent.layer.msg(response.data.msg);
                let index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }).catch(function (error) {
                layer.msg(error.message)
            })
        }
    },
    created: function () {
        this.appVersion = parent.layer.appVersion;
    }
})