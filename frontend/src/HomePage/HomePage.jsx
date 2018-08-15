import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
 
import { userActions } from '../_actions';
 
class HomePage extends React.Component {
    state = {
        message: []
    }
    exampleSocket = new WebSocket('wss://echo.websocket.org')

    componentDidMount() {
        this.props.dispatch(userActions.getAll());
        this.exampleSocket.onopen = (event) => {
            console.log(this.exampleSocket)
            this.exampleSocket.send("topic/hello");
            console.log(event); 
        };

        this.exampleSocket.onmessage = evt => { 
            // add the new message to state
              this.setState({
              messages : this.state.messages.concat([ evt.data ])
            })
          };

        setInterval(() => {
            console.log('test')
            this.exampleSocket.send('test')
        }, 2000)
        this.exampleSocket.onmessage = (event) => {
            console.log(event);
            console.log('reoginre')
        }

        this.exampleSocket.addEventListener('message',(e) => {
            this.exampleSocket.onmessage(e);
          })
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
{/*                 <h3>All registered users:</h3>
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
                } */}
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