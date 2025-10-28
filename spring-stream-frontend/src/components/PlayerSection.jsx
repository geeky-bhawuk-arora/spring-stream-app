import { useEffect, useRef, useState } from 'react';
import { Play, AlertCircle } from 'lucide-react';
import { getVideoStreamUrl } from '../services/uploadService';

export default function PlayerSection({ videoId }) {
  const videoRef = useRef(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!videoId || !videoRef.current) return;

    const video = videoRef.current;
    const streamUrl = getVideoStreamUrl(videoId);
    
    setLoading(true);
    setError(null);

    // Check if browser supports HLS natively (Safari)
    if (video.canPlayType('application/vnd.apple.mpegurl')) {
      video.src = streamUrl;
      video.addEventListener('loadedmetadata', () => {
        setLoading(false);
      });
      video.addEventListener('error', () => {
        setError('Failed to load video');
        setLoading(false);
      });
    } 
    // For other browsers, we'd need HLS.js library
    // You should install it: npm install hls.js
    else if (window.Hls && window.Hls.isSupported()) {
      const hls = new window.Hls({
        debug: false,
        enableWorker: true,
        lowLatencyMode: true,
      });

      hls.loadSource(streamUrl);
      hls.attachMedia(video);
      
      hls.on(window.Hls.Events.MANIFEST_PARSED, () => {
        setLoading(false);
        console.log('HLS manifest loaded');
      });

      hls.on(window.Hls.Events.ERROR, (event, data) => {
        console.error('HLS Error:', data);
        if (data.fatal) {
          setError(`Playback error: ${data.type}`);
          setLoading(false);
        }
      });

      return () => {
        hls.destroy();
      };
    } else {
      setError('HLS is not supported in this browser');
      setLoading(false);
    }
  }, [videoId]);

  return (
    <div className="space-y-4">
      <div>
        <h2 className="text-lg font-semibold text-gray-900 mb-3">Video Player</h2>
        <div className="bg-black rounded-lg overflow-hidden aspect-video">
          {error ? (
            <div className="w-full h-full flex items-center justify-center text-center p-4">
              <div>
                <AlertCircle className="w-12 h-12 text-red-500 mx-auto mb-2" />
                <p className="text-red-400 text-sm">{error}</p>
              </div>
            </div>
          ) : loading ? (
            <div className="w-full h-full flex items-center justify-center">
              <div className="text-center">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-white mx-auto mb-2"></div>
                <p className="text-gray-400 text-sm">Loading video...</p>
              </div>
            </div>
          ) : (
            <video
              ref={videoRef}
              controls
              className="w-full h-full"
              autoPlay
              playsInline
            >
              Your browser does not support video playback.
            </video>
          )}
        </div>
        <div className="mt-2 space-y-1">
          <p className="text-xs text-gray-500">
            Video ID: <span className="font-mono">{videoId}</span>
          </p>
          <p className="text-xs text-gray-500">
            Stream URL: <span className="font-mono">{getVideoStreamUrl(videoId)}</span>
          </p>
        </div>
      </div>
    </div>
  );
}