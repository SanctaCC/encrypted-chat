import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import { userActions } from '../_actions';

import styled, { css } from 'styled-components';

const Container = styled.div`
    margin: 0px;
    padding: 0px;
    width: 100%;
    min-width: 100vh;
    color: #242A31;
    background: #F5F7F9;
    display: flex;
    justify-content: center;
    align-items: stretch;
    flex-direction: column;
`

const Title = styled.h1`
    margin: 50px auto;
`

const FormWrapper = styled.div`
    width: 100%;
    max-width: 460px;
    border: 1px solid #d4dadf;
    border-radius: 3px;
    margin: auto;
    background: #fff;
`

const LabelText = styled.label`
    font-size: 12px;
    line-height: 1.2;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 1.2px;
    color: #74818D;
`

const InputWrapper = styled.div`
    border-radius: 3px;
    outline: none;
    border: 1px solid #D3DCE4;
    width: 100%;
    box-sizing: border-box;
    display: flex;
    background: #FFFFFF;
    font-size: 16px;
    font-weight: 400;
    line-height: 1.625;
`
const Button = styled.button`
    border-radius: 3px;
    background: transparent;
    border: 2px solid #3884FF;
    width: 100%;
    line-height: 1em;
    box-sizing: border-box;
    font-weight: 700;
    color: #fff;
    border-color: transparent;
    text-align: center;
    height: 40px;
    padding: 0px 24px;
    cursor: pointer;
    font-size: 16px;

  ${props => props.primary && css`
    background: #3884FF;
    color: white;
  `}
`

const helpBlock = {
    color: 'red',
    fontSize: '12px',
}

const hyperLink = {
    color: '#3884FF',
    textDecoration: 'underline',
}

class RegisterPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            user: {
                username: '',
                password: ''
            },
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const { name, value } = event.target;
        const { user } = this.state;
        this.setState({
            user: {
                ...user,
                [name]: value
            }
        });
    }

    handleSubmit(event) {
        event.preventDefault();

        this.setState({ submitted: true });
        const { user } = this.state;
        const { dispatch } = this.props;
        if (user.username && user.password) {
            dispatch(userActions.register(user));
        }
    }

    render() {
        const { registering } = this.props;
        const { user, submitted } = this.state;
        return (
            <Container>
                <Title>
                    Encrypted Chat
                </Title>
                <FormWrapper>
                    <h2
                        style={{
                            padding: '32px 24px',
                            textAlign: 'center',
                        }}>
                        Create an account
                    </h2>
                    <form
                        style={{
                            padding: '32px 24px',
                            borderTop: '1px solid #E6ECF1'
                        }}
                        name="form"
                        onSubmit={this.handleSubmit}>

                        <div style={{
                            marginBottom: '24px'
                        }}>
                            <label
                                style={{
                                    'display': 'block',
                                    'marginBottom': '1%'
                                }}
                                htmlFor="username">
                                <LabelText>Email</LabelText>
                            </label>
                            <InputWrapper className={'form-group' + (submitted && !user.username ? ' has-error' : '')}>
                                <input
                                    style={{
                                        flex: 'auto',
                                        border: 'none',
                                        outline: 'none',
                                        margin: '0px',
                                        width: '100%',
                                        background: 'transparent',
                                        resize: 'none',
                                        borderRadius: '3px',
                                        height: '38px',
                                        padding: '0px 8px',
                                    }}
                                    type="text"
                                    name="username"
                                    value={user.username}
                                    onChange={this.handleChange}
                                    placeholder="you@email.com"
                                />
                            </InputWrapper>
                            {submitted && !user.username &&
                                <div style={helpBlock}>Username is required</div>
                            }
                        </div>
                        <div style={{
                            marginBottom: '24px'
                        }}>
                            <label
                                style={{
                                    'display': 'block',
                                    'marginBottom': '1%'
                                }}
                                htmlFor="password">
                                <LabelText>Password</LabelText>
                            </label>
                            <InputWrapper className={'form-group' + (submitted && !user.password ? ' has-error' : '')}>
                                <input
                                    style={{
                                        flex: 'auto',
                                        border: 'none',
                                        outline: 'none',
                                        margin: '0px',
                                        width: '100%',
                                        background: 'transparent',
                                        resize: 'none',
                                        borderRadius: '3px',
                                        height: '38px',
                                        padding: '0px 8px',
                                    }}
                                    type="password"
                                    className="form-control"
                                    name="password"
                                    value={user.password}
                                    onChange={this.handleChange}
                                    placeholder="Password (at least 6 characters)"
                                />
                            </InputWrapper>
                            {submitted && !user.password &&
                                <div style={helpBlock}>Password is required</div>
                            }
                        </div>
                        <div style={{
                            color: '#9DAAB6',
                            fontSize: '12px',
                            lineHeight: '1.625',
                            fontWeight: '400',

                        }}>
                            <p style={{ margin: 0 }}>
                                By clicking "Create your account" below, you agree to our&nbsp;
                                <span
                                    style={hyperLink}>
                                    terms of service
                                </span> <br />and&nbsp;
                                <span
                                    style={hyperLink}>
                                    privacy statement
                                </span>.
                                We’ll occasionally send you account related emails.
                            </p>
                        </div>
                        <div className="form-group">
                            <Button primary>Create your account</Button>
                            {registering &&
                                <img src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
                            }
                        </div>
                    </form>
                </FormWrapper>
                <Link
                    to="/login"
                    style={{
                        margin: 'auto',
                        fontSize: '12px',
                        lineHeight: 1.5,
                        fontWeight: 500,
                        textDecoration: 'none',
                        color: '#9DAAB6',
                    }}>
                    Already have an account? <span style={hyperLink}>Sign In</span>
                </Link>

            </Container>
        );
    }
}

function mapStateToProps(state) {
    const { registering } = state.registration;
    return {
        registering
    };
}

const connectedRegisterPage = connect(mapStateToProps)(RegisterPage);
export { connectedRegisterPage as RegisterPage };