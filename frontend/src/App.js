import styled from 'styled-components/macro'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { AppHeader } from './components/AppHeader'
import { ImageUpload } from './components/ImageUpload'
import { Gallery } from './components/Gallery'

export default function App() {
  const [photos, setPhotos] = useState([])
  const [status, setStatus] = useState('loading')
  const [error, setError] = useState()

  const fetchPhotos = () => {
    setStatus('loading')
    axios
      .get('photo')
      .then(res => res.data)
      .then(setPhotos)
      .then(() => setStatus('success'))
      .catch(error => {
        setError(error)
        setStatus('error')
      })
  }

  useEffect(() => {
    fetchPhotos()
  }, [])

  const isLoading = status === 'loading'
  const isError = status === 'error'
  const isSuccess = status === 'success'

  return (
    <Wrapper>
      <AppHeader />
      {isError && <p>Something terrible just happened: {error.message}</p>}
      {isLoading && <p>loading</p>}
      {isSuccess && <Gallery photos={photos} onDelete={fetchPhotos} />}
      <ImageUpload onUpload={fetchPhotos} />
    </Wrapper>
  )
}

const Wrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: grid;
  text-align: center;
  grid-template-rows: min-content 1fr;
  padding-bottom: 64px;
`
