import MailSlurp from "mailslurp-client";

const mailslurp = new MailSlurp({apiKey: "e3f49834f837bcd8b3729794eb8be72bf9352569f25130cbe095c947cbf56b85"});
const fromId = "be442352-60e7-4da9-82b4-7ba5d93bb31b";

export default {
sendEmail(email, messages) {
    let string = "";
    for (let i = 0; i < messages.length; i++) {
        string = string + messages[i].text + " - " + messages[i].author + " || ";
    }
    mailslurp.sendEmail(fromId, {
    to: [email],
    subject: "TEChatBot History",
    body: string
})
}
}