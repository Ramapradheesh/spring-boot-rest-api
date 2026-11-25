
import { useState, useEffect } from 'react'
import axios from 'axios'
import './App.css'

const API_URL = 'http://localhost:8080/api'

function App() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const [user, setUser] = useState(
    localStorage.getItem('username') || ''
  )

  const [account, setAccount] = useState(null)
  const [accountLoading, setAccountLoading] = useState(false)
  const [amount, setAmount] = useState('')
  const [depositLoading, setDepositLoading] = useState(false)

  // Fetch account details
  useEffect(() => {
    const fetchAccount = async () => {
      const token = localStorage.getItem('token')

      if (!token || !user) return

      setAccountLoading(true)
      setError('')

      try {
        const response = await axios.get(
          `${API_URL}/accounts/me`,
          {
            headers: {
              Authorization: `Bearer ${token}`
            }
          }
        )

        setAccount(response.data)
      } catch (err) {
        setError(
          err.response?.data?.message ||
          'Unable to load your bank account.'
        )
      } finally {
        setAccountLoading(false)
      }
    }

    fetchAccount()
  }, [user])

  // Login
  const handleLogin = async (e) => {
    e.preventDefault()
    setError('')
    setLoading(true)

    try {
      const response = await axios.post(
        `${API_URL}/auth/login`,
        { username, password }
      )

      localStorage.setItem('token', response.data.token)
      localStorage.setItem('username', response.data.username)

      setUser(response.data.username)
      setAccount(null)
    } catch (err) {
      setError(
        err.response?.data?.message ||
        'Login failed. Check your credentials.'
      )
    } finally {
      setLoading(false)
    }
  }

  // Deposit money
  const handleDeposit = async (e) => {
    e.preventDefault()
    setError('')

    if (!amount || Number(amount) <= 0) {
      setError('Enter a valid deposit amount.')
      return
    }

    setDepositLoading(true)

    try {
      const token = localStorage.getItem('token')

      const response = await axios.post(
        `${API_URL}/accounts/deposit`,
        { amount: Number(amount) },
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      )

      setAccount({
        accountNumber: response.data.accountNumber,
        balance: response.data.balance
      })

      setAmount('')
    } catch (err) {
      setError(
        err.response?.data?.message || 'Deposit failed.'
      )
    } finally {
      setDepositLoading(false)
    }
  }

  // Logout
  const handleLogout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('username')

    setUser('')
    setAccount(null)
    setUsername('')
    setPassword('')
    setAmount('')
    setError('')
  }

  // Dashboard
  if (user) {
    return (
      <main className="dashboard">
        <nav className="navbar">
          <h2>RP Bank</h2>
          <button onClick={handleLogout}>Logout</button>
        </nav>

        <section className="welcome">
          <p>YOUR BANKING DASHBOARD</p>
          <h1>Welcome, {user}!</h1>
          <p>You are successfully authenticated.</p>

          {error && <p className="error">{error}</p>}

          {accountLoading ? (
            <div className="account-card">
              Loading account details...
            </div>
          ) : account ? (
            <>
              <div className="account-card">
                <span>Available Balance</span>

                <h1>
                  ₹{Number(account.balance).toLocaleString('en-IN', {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2
                  })}
                </h1>

                <p>Account Number</p>
                <h3>{account.accountNumber}</h3>

                <h2>Active</h2>
                <p>Secure JWT Banking Session</p>
              </div>

              <div className="account-card">
                <h2>Deposit Money</h2>

                <form onSubmit={handleDeposit}>
                  <input
                    type="number"
                    placeholder="Enter amount"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    min="0.01"
                    step="0.01"
                    required
                  />

                  <button
                    className="login-button"
                    type="submit"
                    disabled={depositLoading}
                  >
                    {depositLoading ? 'Depositing...' : 'Deposit'}
                  </button>
                </form>
              </div>
            </>
          ) : (
            <div className="account-card">
              <p>No bank account details available.</p>
            </div>
          )}
        </section>
      </main>
    )
  }

  // Login page
  return (
    <main className="login-page">
      <section className="login-card">
        <div className="brand">
          <div className="brand-icon">R</div>
          <span>RP BANK</span>
        </div>

        <p className="eyebrow">WELCOME BACK</p>
        <h1>Banking made simple.</h1>

        <p className="subtitle">
          Sign in to securely access your banking dashboard.
        </p>

        <form onSubmit={handleLogin}>
          <label>Username</label>

          <input
            type="text"
            placeholder="Enter your username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />

          <label>Password</label>

          <input
            type="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          {error && <p className="error">{error}</p>}

          <button
            className="login-button"
            type="submit"
            disabled={loading}
          >
            {loading ? 'Signing in...' : 'Sign In'}
          </button>
        </form>

        <p className="security-note">
          Protected with secure JWT authentication
        </p>
      </section>
    </main>
  )
}

export default App
