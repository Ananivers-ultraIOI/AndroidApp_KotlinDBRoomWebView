package be.heh.kotlindbsql.db

class User(i : Int) {
    private var id : Int = 0
    var login = "null"
        private set
    var pwd: String = "null"
        private set
    var email: String = "null"
        private set
    constructor(i: Int, l: String,
                p: String, e: String ) : this(i)
    {
        id = i
        login = l
        pwd = p
        email = e
    }
    override fun toString() : String {
        val sb = StringBuilder()
        sb.append("ID : " + id.toString() +
                "\n" +
                "Login : " + login + "\n" +
                "Password : " + pwd + "\n" +
                "Email : " + email)
        return sb.toString()
    }
}