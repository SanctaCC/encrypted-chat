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

class LoginPage extends React.Component {
	constructor(props) {
		super(props);

		// reset login status
		this.props.dispatch(userActions.logout());

		this.state = {
			username: '',
			password: '',
			submitted: false
		};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(e) {
		const { name, value } = e.target;
		this.setState({ [name]: value });
	}

	handleSubmit(e) {
		e.preventDefault();

		this.setState({ submitted: true });
		const { username, password } = this.state;
		const { dispatch } = this.props;
		if (username && password) {
			dispatch(userActions.login(username, password));
		}
	}

	render() {
		const { loggingIn } = this.props;
		const { username, password, submitted } = this.state;
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
						Sign in to Encrypted Chat
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
							<InputWrapper className={'form-group' + (submitted && !username ? ' has-error' : '')}>
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
									value={username}
									onChange={this.handleChange}
									placeholder="you@email.com"
								/>
							</InputWrapper>
							{submitted && !username &&
								<div style={helpBlock}>Email is required</div>
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
							<InputWrapper className={'form-group' + (submitted && !password ? ' has-error' : '')}>
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
									name="password"
									value={password}
									onChange={this.handleChange}
									placeholder="Password"
								/>
							</InputWrapper>
							{submitted && !password &&
								<div style={helpBlock}>Password is required</div>
							}
						</div>
						<div className="form-group">
							<Button primary>Login</Button>
							{loggingIn &&
								<img alt='loader' src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
							}
						</div>
					</form>
				</FormWrapper>
				<Link
					to="/register"
					style={{
						margin: 'auto',
						fontSize: '12px',
						lineHeight: 1.5,
						fontWeight: 500,
						textDecoration: 'none',
						color: '#9DAAB6',
					}}>
					New to Encrypted Chat? <span style={hyperLink}>Create an account</span>
				</Link>

			</Container>
		);
	}
}

function mapStateToProps(state) {
	const { loggingIn } = state.authentication;
	return {
		loggingIn
	};
}

const connectedLoginPage = connect(mapStateToProps)(LoginPage);
export { connectedLoginPage as LoginPage };