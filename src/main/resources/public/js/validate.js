$("document").ready(function() {
  //sign up  form validation
  $("form[name='signUpForm']").validate({
    rules: {
      username: "required",
      email: "required",
      phonenumber: "required",
      password: "required"
    },
    messages: {
      username: "Please Indicate your Username",
      email: "Please inidicate a valid email",
      phonenumber: "Phone number is required",
      password: "Please provide a valid password"
    },
    submitHandler: function(form) {
      form.submit();
      event.preventDefault();
    }
  });

  //sign in form validation

  $("form[name='signInForm']").validate({
    rules: {
      email: "required",
      password: "required"
    },
    messages: {
      email: "Please inidicate a valid email",
      password: "Please provide a valid password"
    },
    submitHandler: function(form) {
      form.submit();
      event.preventDefault();
    }
  });
});
