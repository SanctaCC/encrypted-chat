import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { userActions } from '../_actions';
var SockJS = require('sockjs-client');
var Stomp = require('stomp-websocket');
 
 
class HomePage extends React.Component {
    state = {
        message: []
    }

    componentDidMount() {
        // let stompClient = null;
        // const wsSourceUrl = "http://localhost:8080/ws";
        // var socket = new SockJS(wsSourceUrl);
        // stompClient = Stomp.over(socket);
        // stompClient.connect({}, function (frame) {
        //     console.log('Connected: ' + frame);
        //     stompClient.subscribe('/topic/hello', function (greeting) {
        //         console.log('test')
        //         console.log(greeting)
        //     });
        //     stompClient.send("/app/test", {}, JSON.stringify({'name': 'test'}));
        // });

        // setInterval(() => {
        //     stompClient.send("/app/test", {}, JSON.stringify({'name': 'test'}));
        // }, 2000)
    }
 
    handleDeleteUser(id) {
        return (e) => this.props.dispatch(userActions.delete(id));
    }

 
    render() {
        const { user, users } = this.props;

        return (
            <div className="col-md-6 col-md-offset-3">
                <h1>Hi {user.username}!</h1>
                <p>You're logged in with React!!</p>
                <h3>All registered users:</h3>
                {users.loading && <em>Loading users...</em>}
                {users.items &&
                    <ul>
                        {users.items.map((user, index) =>
                            <li key={user.id}>
                                {user.username}
                                {
                                    user.deleting ? <em> - Deleting...</em>
                                    : user.deleteError ? <span className="error"> - ERROR: {user.deleteError}</span>
                                    : <span> - <a onClick={this.handleDeleteUser(user.id)}>Delete</a></span>
                                }
                            </li>
                        )}
                    </ul>
                } 
                <p>
                    <Link to="/login">Logout</Link>
                </p>
            </div>
        );
    }
}
 
function mapStateToProps(state) {
    const { users, authentication } = state;
    const { user } = authentication;
    return {
        user,
        users
    };
}
 
const connectedHomePage = connect(mapStateToProps)(HomePage);
export { connectedHomePage as HomePage };