var auth={
    init:function(){
        var _this=this;

        var user=$('#user').text();
        var author=$('#author').val();
        if(user==author){
            $('#title').removeAttr('readonly');
            $('#content').removeAttr('readonly');
        }
    }
}
auth.init();