function validform() {

    const a = document.forms["my-form"]["name"].value;
    const b = document.forms["my-form"]["email"].value;
    const c = document.forms["my-form"]["login"].value;
    const d = document.forms["my-form"]["pass"].value;
    const e = document.forms["my-form"]["surname"].value;

    if (a==null || a==="")
    {
        alert("Please Enter Your Name");
        return false;
    }else if (b==null || b==="")
    {
        alert("Please Enter Your Email Address");
        return false;
    }else if (c==null || c==="")
    {
        alert("Please Enter Your Login");
        return false;
    }else if (d==null || d==="")
    {
        alert("Please Enter Your Password");
        return false;
    }else if (e==null || e==="")
    {
        alert("Please Enter Your Surname");
        return false;
    }
}