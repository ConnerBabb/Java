<template>
  <section class="chat-box">
    <div class="chat-box-list-container" ref="chatbox">
      <ul class="chat-box-list">
        <li class="message" v-for="(message, id) in messages" :key="id" :class="message.author" >
          <p>{{message.text}}
            <ul>
          <li class="responselist"  v-for="(link, id) in message.links" :key="id"><a v-bind:href="link.link" target="_blank"> {{link.title}} </a></li>
          </ul>
          </p>
        </li>
      </ul>
    </div>
    <div class="chat-inputs">
      <input type="text" v-model="message" @keyup.enter="sendMessage" />
      <button @click="sendMessage">Send</button>
    </div>
  </section>
</template>

<script>
import emailService from "../services/EmailService";
import motivateService from "../services/MotivateService";
import questionService from "../services/QuestionService";
import JobSearchService from '../services/JobSearchService';

export default {
  created() {
    this.messages.push({
      text: "Hello I am the TE Digital Assitant what is your name?",
      author: "server",
    });
    
  },
  name: "ChatBox",
  data: () => ({
    username: "",
    message: "",
    messages: [],
    searchLocation: "",
    searchDescription: "",
    email: "",
  }),
  methods: {
    completed() {
      this.messages.push({
                text: "Is there anything else I can assist you with today?",
                author: 'server'
            });
            this.$nextTick(() => {
            this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
          });
    },
    getLocation() {
      this.searchParams.location = this.message.text; 
    this.messages.push({
                text: this.searchParams.location + "City",
                author: 'server'
            });

    },
    greetUser() {
        this.username = this.message;
            this.messages.push({
                text: "Hello " + this.username + "! What can I assist you with today?",
                author: 'server'
            });
    },
    helpUser() {
                let string = "I can help with Curriculum and Pathway, just ask me a question! Or if you want to search job postings tell me you are looking for a job. Need some extra motivation? Just tell me!"
                this.messages.push({
                text: string,
                author: 'server'
                })
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
    },
    motivateUser() {
        motivateService.motivateMe().then( response => {
                let number = Math.floor(Math.random() * response.data.length)
            let quote = response.data[number].text;
            this.messages.push({
                text: quote,
                author: 'server'
            })
            this.completed();
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            })
    },
    answerQuestion() {
      this.messages.push({
                text: "Searching...",
                author: 'server'
            });
       questionService.askQuestion(this.message).then(response => {
            let num = response.data.length;
            this.messages.pop();
            let text = "Here are some links to assist:";
            let links = []
            for (let i = 0; i < num; i++) {
              let link = {
                title: "",
                link: ""
              }
              link.link = response.data[i].mainLink;
              link.title = response.data[i].title; 
              links.push(link);
              if (response.data[i].type == "Zoom") {
              link = {}
              link.title = response.data[i].title + " Transcript"; 
               link.link =response.data[i].transcriptLink;
               links.push(link);
               
              }
            }
            this.messages.push({
                text: text,
                author: 'server',
                links: links
            })
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            this.completed();
          });
          this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
    },
    sendMessage() {
      this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            });
      this.messages.push({
        text: this.message,
        author: "client",
      });
        if (this.messages.length === 2) {
            this.greetUser();
        } else if (this.message.toLowerCase().includes("motivat") || this.message.toLowerCase().includes("inspir")){
           this.motivateUser();
        } else if (this.message.toLowerCase().includes("job")) {
          this.messages.push({
          text: "What location are you interested in searching in?",
          author: 'server'
        });
          this.$nextTick(() => {
            this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
          });
          this.message= '';
        } else if (this.messages[this.messages.length - 2].text.includes("What location")) {
          this.searchLocation = this.messages[this.messages.length - 1].text;
          this.$nextTick(() => {
            this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
          });
          this.message= '';
          this.messages.push({
                text: "What jobs would you like to search for?",
                author: 'server'
            });
        }
        else if (this.messages[this.messages.length - 2].text.includes("What jobs would you like to search for")) {
          this.searchDescription = this.messages[this.messages.length - 1].text;
           this.messages.push({
                text: "Searching...",
                author: 'server'
            });
          this.$nextTick(() => {
            this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
          });
          this.message= '';
            JobSearchService.getJobs(this.searchLocation, this.searchDescription).then(response => {
            let num = response.data.length;
            this.messages.pop();
            if ( num == 0 ) {
               this.messages.push({
                text: "No jobs found please try another search.",
                author: 'server'
            })
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            this.completed();
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            }
            let text = "Here are the top 5 jobs that match your search:";
            let links = [];
            for (let i = 0; i < 5; i++) {
              let link = {
                title: "",
                link: ""
              }
              link.link = response.data[i].url;
              link.title = response.data[i].title;  
              links.push(link);
            }
                this.messages.push({
                text: text,
                author: 'server',
                links: links
            })
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            this.completed();
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            })
       } else if ((this.messages[this.messages.length - 1].text.includes("no")) && (this.messages[this.messages.length - 2].text.includes("Is there anything"))) {
         this.messages.push({
                text: "Would you like a copy of the chat sent to your email?",
                author: 'server'
            });
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
       }
       else if ((this.messages[this.messages.length - 1].text.includes("yes")) && (this.messages[this.messages.length - 2].text.includes("Would you like"))) {
         this.messages.push({
                text: "Please enter your email address.",
                author: 'server'
            });
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })}
             else if ((this.messages[this.messages.length - 1].text.includes("no")) && (this.messages[this.messages.length - 2].text.includes("Would you like"))) {
         this.messages.push({
                text: "Have a nice day!",
                author: 'server'
            });
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            location.reload();
       } else if ((this.messages[this.messages.length - 1].text.includes("@")) && (this.messages[this.messages.length - 2].text.includes("Please enter"))) {
         this.email = this.messages[this.messages.length - 1].text;
          emailService.sendEmail(this.email, this.messages);
         this.messages.push({
                text: "Email sent. Have a nice day!",
                author: 'server'
            });
            this.$nextTick(() => {
                this.$refs.chatbox.scrollTop = this.$refs.chatbox.scrollHeight
            })
            location.reload();
       } else if (this.message.toLowerCase().includes("help") || (this.message.toLowerCase().includes("what") && this.message.toLowerCase().includes("ask"))) {
           this.helpUser();
        }
       else {
            this.answerQuestion();
        }
            this.message= '';
}
}
}
</script>

<style>

.responselist {
  display: inline-flex;
  flex-direction: column;
  margin: 10px;
}
.chat-box, .chat-box-list {
  display: flex;
  flex-direction: column;
  list-style-type: none;
}
.chat-box-list-container {
  overflow-y: scroll;
}
.chat-box-list {
  padding-left: 10px;
  padding-right: 10px;
}
.chat-box-list .server p {
  padding: 8px;
  color: rgb(94, 91, 91);
  border-radius: 4px;
  background: #8ee5fff1;
  float: left;
  
}
.chat-box-list .client p {
  padding: 8px;
  color: white;
  border-radius: 4px;
  background: #030ffcee;
  float: right;
  
}
.chat-box {
  margin: 10px;
  border: 1px solid #999;
  width: 50vw;
  height: 50vh;
  border-radius: 4px;
  margin-left: auto;
  margin-right: auto;
  align-items: space-between;
  justify-content: space-between;
  background-image: url("../../public/TechElevator.png");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}
.responselist a {
  color: #053333;
}
.chat-inputs {
  display: flex;
}
.chat-inputs input {
  line-height: 3;
  width: 100%;
  border: 1px solid #999;
  border-left: none;
  border-bottom: none;
  border-right: none;
  border-bottom-left-radius: 4px;
  padding-left: 15px;
}
.chat-inputs button {
  width: 145px;
  color: white;
  background: #0070c8;
  border-color: #999;
  border-bottom: none;
  border-right: none;
  border-bottom-right-radius: 3px;
}

</style>