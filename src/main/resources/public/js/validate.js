$("document").ready(function(){
//sign up validation form
$("form[name='signUpForm']").validate({
rules:{
username:"required",
email:"required",
phonenumber:"required",
password:"required"
},
messages:{
username:"Please Indicate your Username",
email:"Please inidicate a valid email",
phonenumber:"Phone number is required",
password:"Please provide a valid email address"
},
submitHandler:function(form){
form.submit();
}
});
})