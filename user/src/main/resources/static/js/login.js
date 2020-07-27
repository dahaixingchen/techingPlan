var profile = new Vue({
    el:"#profile",
    data:{
        userBo:{
            // name:'feifei',
            phone:'',
            password:'',
            flags:true
        }
    },
    methods:{
        getmenu:function () {
            var that = this;
            axios.post("http://localhost:39001/user/login",this.userBo).then(function (response) {

            // axios.get("http://localhost:39001/teach/query-allTeach?id="+this.id).then(function (response) {
            // axios.get("http://47.112.115.39:39001/public/menuQueryQuestions").then(function (response) {
                console.log(response.data)
                console.log(response.data.meessage)
                console.log(response)
            //     alert(response.data)
                if (JSON.stringify(response.data) == "{}"){

                }else{
                    alert(response.data.message);
                }
            })
        }
    }
})