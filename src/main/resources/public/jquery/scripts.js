
$("#btnSubmit").click(function (event) {
  var userName = $("#name").val();
  var userEmail = $("#email").val();
  var userMessage = $(".message").val();
  /** user logic interface */
  if ((userName !== "") && (userEmail !== "") && (userMessage != "")) {
    alert("Fill in details");
  }

  event.preventDefault();
});