import React from 'react'
import { connect } from 'react-redux'
import { userActions } from '../_actions'
import { Navbar } from './components/Navbar/Navbar'
import { UserList } from './components/UserList/UserList'
import SockJS from 'sockjs-client'
import Stomp  from 'stomp-websocket'


class HomePage extends React.Component {
  state = {
    message: []
  }

  componentDidMount() {
    this.props.dispatch(userActions.getAll());
    this.testSockets();
  }

  testSockets() {
    let stompClient = null;
    const wsSourceUrl = "http://localhost:8080/ws";
    var socket = new SockJS(wsSourceUrl);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/hello', function (greeting) {
        console.log('test')
        console.log(greeting)
      });
      stompClient.send("/app/test", {}, JSON.stringify({ 'name': 'test' }));
    });
  }

  // handleDeleteUser(id) {
  //   return (e) => this.props.dispatch(userActions.delete(id));
  // }


  render() {
    //const { user, users } = this.props;

    return (
      <div style={{
        width: '100vw',
        height: '100vh',
      }}>
        <Navbar></Navbar>
        <UserList></UserList>
        {/* {users.loading && <em>Loading users...</em>}
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
        } */}
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